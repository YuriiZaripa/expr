package ua.okwine.productexpirationdate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.entity.OkwineStockBalance;
import ua.okwine.productexpirationdate.repository.SkuRepository;
import ua.okwine.productexpirationdate.repository.StockBalanceRepository;
import ua.okwine.productexpirationdate.rest.dto.parsing.OkwineMarketResiduesResponseDTO;
import ua.okwine.productexpirationdate.rest.dto.parsing.OkwineProductsResponseSingleProductDTO;
import ua.okwine.productexpirationdate.service.clients.OkwineClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParserService {

    private static final String IMAGE_URL_PREFIX = "https://content.okwine.ua/files/";
    private static final String TARGET_MARKET_ARTICLE = "ЦБ0000009";

    private final OkwineClient okwineClient;
    private final SkuRepository skuRepository;
    private final StockBalanceRepository stockBalanceRepository;

    public void parsingOkwineData() {
        List<OkwineStockBalance> stockBalances = new ArrayList<>();
        var sku = skuRepository.findByImageIsNull();
        if (sku.isEmpty()) {
            log.info("All data up to date. Parsing is stopped.");

            return;
        }
        log.info("Starting parsing sku from Okwine API");

        var productsFromAPI = okwineClient.getAllProductsData();

        Map<String, OkwineProductsResponseSingleProductDTO> utpProductMap = productsFromAPI.stream()
                .collect(Collectors.toMap(OkwineProductsResponseSingleProductDTO::getUtp,
                        product -> product,
                        (existing, replacement) -> existing));

        sku.forEach(product -> {
            var okwineProduct = utpProductMap.getOrDefault(product.getVendorCode(), null);
            if (okwineProduct == null) {
                return;
            }

            var imageURLs = okwineProduct.getImages();
            product.setImage(imageURLs != null && !imageURLs.isEmpty()
                    ? IMAGE_URL_PREFIX + imageURLs.get(0)
                    : null);
            var stockBalance = new OkwineStockBalance(okwineProduct.getPr_id(), 0);
            product.setStockBalance(stockBalanceRepository.save(stockBalance));

        });

        skuRepository.saveAll(sku);
        log.info("Starting parsing sku from Okwine API");
    }

    public void updateSockBalance() {
        var balances = stockBalanceRepository.findAll();

        log.info("Starting updating products balances from Okwine API");
        balances.forEach(stockBalance -> {
            var balancesResponse = okwineClient.getStockBalanceByOkwineProductId(stockBalance.getOkwineProductId());

            Integer balanceResponse = balancesResponse == null
                    ? null
                    : balancesResponse.stream()
                    .filter(market -> market.getMarket_id().getArticle().equals(TARGET_MARKET_ARTICLE))
                    .findFirst()
                    .map(OkwineMarketResiduesResponseDTO::getCount)
                    .orElse(null);

            stockBalance.setStockBalance(balanceResponse);
            log.info("Balance by id " + stockBalance.getOkwineProductId() + " to " + balanceResponse + ". ");

        });

        stockBalanceRepository.saveAll(balances);
        log.info("Finished  updating products balances from Okwine API");
    }
}