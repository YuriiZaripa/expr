package ua.okwine.productexpirationdate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.rest.dto.parsing.OkwineResponseProductDTO;
import ua.okwine.productexpirationdate.service.clients.OkwineClient;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParserService {

    private static final String IMAGE_URL_PREFIX = "https://content.okwine.ua/files/";

    private final OkwineClient okwineClient;
    private final ProductService productService;

    public void parsingOkwineData() {
        var products = productService.findAllWithEmptyImage();
        if (products.isEmpty()) {
            log.info("All data up to date. Parsing is stopped.");

            return;
        }
        log.info("Starting parsing products from Okwine API");

        var productsFromAPI = okwineClient.getAllProductsData();

        Map<String, String> utpImageMap = productsFromAPI.stream()
                .collect(Collectors.toMap(OkwineResponseProductDTO::getUtp,
                        product -> product.getImages() != null && product.getImages().isEmpty()
                                ? ""
                                : product.getImages().get(0).trim(),
                        (existing, replacement) -> existing));

        products.stream()
                .forEach(product -> {
                    String imageURL = utpImageMap.getOrDefault(product.getVendorCode(), null);

                    product.setImage(imageURL != null ? IMAGE_URL_PREFIX + imageURL : null);
                });

        productService.updateAll(products);
        log.info("Starting parsing products from Okwine API");
    }
}
