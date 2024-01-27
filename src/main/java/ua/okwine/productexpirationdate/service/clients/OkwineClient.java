package ua.okwine.productexpirationdate.service.clients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import ua.okwine.productexpirationdate.entity.dto.parsing.OkwineResponse;
import ua.okwine.productexpirationdate.entity.dto.parsing.OkwineResponseProductDTO;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OkwineClient {

    private final RestTemplate okwineSource;
    private final static String OKWINE_SOURCE_URL = "https://okwine.ua/api/v1/filter/products";
    private final static String PAGE = "?page=";

    public List<OkwineResponseProductDTO> getAllProductsData() {
        var data = okwineSource.getForEntity(OKWINE_SOURCE_URL, OkwineResponse.class);
        var okwineResponseBody = data.getBody();
        int maxPage = okwineResponseBody.getData().getMaxPage();
        int pageNumber = 1;
        String requestURITemplate = OKWINE_SOURCE_URL + PAGE;
        List<OkwineResponseProductDTO>  products = new ArrayList<>();

        log.info("Starting downloading products from Okwine API");
        while(pageNumber <= maxPage) {
            try {
                log.info("Parsing Okwine API on page " + pageNumber);
                data = okwineSource.getForEntity(requestURITemplate + pageNumber, OkwineResponse.class);
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
}
