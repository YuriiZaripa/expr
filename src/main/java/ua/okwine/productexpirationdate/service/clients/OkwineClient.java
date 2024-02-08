package ua.okwine.productexpirationdate.service.clients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import ua.okwine.productexpirationdate.rest.dto.parsing.OkwineMarketResiduesResponseDTO;
import ua.okwine.productexpirationdate.rest.dto.parsing.OkwineProductsResponse;
import ua.okwine.productexpirationdate.rest.dto.parsing.OkwineProductsResponseSingleProductDTO;
import ua.okwine.productexpirationdate.rest.dto.parsing.OkwineResiduesResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OkwineClient {

    private final RestTemplate okwineSource;
    private final static String OKWINE_PRODUCTS_URL = "https://okwine.ua/api/v1/filter/products";
    private final static String PAGE = "?page=";
    private final static String OKWINE_PRODUCT_STOCK_BALANCES_URL = "https://okwine.ua/api/v1/product/residues/";

    public List<OkwineProductsResponseSingleProductDTO> getAllProductsData() {
        var data = okwineSource.getForEntity(OKWINE_PRODUCTS_URL, OkwineProductsResponse.class);
        var okwineResponseBody = data.getBody();
        int maxPage = okwineResponseBody.getData().getMaxPage();
        int pageNumber = 1;
        String requestURITemplate = OKWINE_PRODUCTS_URL + PAGE;
        List<OkwineProductsResponseSingleProductDTO> products = new ArrayList<>();

        log.info("Starting downloading products from Okwine API");
        while (pageNumber <= maxPage) {
            try {
                log.info("Parsing Okwine API on page " + pageNumber);
                data = okwineSource.getForEntity(requestURITemplate + pageNumber, OkwineProductsResponse.class);
                products.addAll(data.getBody().getData().getData());
            } catch (HttpServerErrorException e) {
                log.error("Okwine server error on page " + pageNumber +
                        ". \"" + requestURITemplate + pageNumber + "\".");
            }
            pageNumber++;
        }
        log.info("Downloading products finished");

        return products;
    }

    public List<OkwineMarketResiduesResponseDTO> getStockBalanceByOkwineProductId(String id) {
        try {
            var data = okwineSource.getForEntity(OKWINE_PRODUCT_STOCK_BALANCES_URL + id, OkwineResiduesResponseDTO.class);

            return data.getBody().getData().getMarkets();
        } catch (HttpClientErrorException e) {
            log.error("Okwine API: not found stock balance for okwine product id " + id + ".");
            return null;
        }
    }
}
