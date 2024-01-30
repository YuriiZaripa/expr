package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.repository.ProductRepository;
import ua.okwine.productexpirationdate.rest.dto.ProductDTO;
import ua.okwine.productexpirationdate.rest.dto.mapper.ProductMapper;
import ua.okwine.productexpirationdate.rest.dto.requestWrappers.ProductRequest;
import ua.okwine.productexpirationdate.rest.dto.requestWrappers.ProductRequestWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReportService {

    //    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductRequestWrapper findAllToDaileReport() {
        List<ProductDTO> products1 = productMapper
                .toProductDTOList(productRepository.findAllNotReportedByAdvanceNotice("не забирают возвраты"));
        List<ProductDTO> products2 = productMapper
                .toProductDTOList(productRepository.findAllNotReportedByAdvanceNotice("физобмен"));
        List<ProductDTO> products3 = productMapper
                .toProductDTOList(productRepository.findAllNotReportedByAdvanceNotice(""));

        ProductRequestWrapper productRequestWrapper = new ProductRequestWrapper(
                toProductRequest(products1),
                toProductRequest(products2),
                toProductRequest(products3)
        );

        return productRequestWrapper;
    }

    private List<ProductRequest> toProductRequest(List<ProductDTO> products) {
        List<ProductRequest> productRequests = new ArrayList<>();

        for (ProductDTO product : products) {
            productRequests.add(new ProductRequest(product));
        }

        return productRequests;
    }

    public void productsProcessing(ProductRequestWrapper requestWrapper) {
        List<UUID> productsId = removeZeroQuantity(requestWrapper.getProductRequestList1());
        productsId.addAll(removeZeroQuantity(requestWrapper.getProductRequestList2()));
        productsId.addAll(removeZeroQuantity(requestWrapper.getProductRequestList3()));

        productRepository.deleteAllById(productsId);
    }

    private List<UUID> removeZeroQuantity(List<ProductRequest> products) {
        List<UUID> productsId = new ArrayList<>();

        ListIterator<ProductRequest> productsIterator = products.listIterator();
        while (productsIterator.hasNext()) {
            ProductRequest product = productsIterator.next();

            if (product.getQuantity() == 0) {
                productsId.add(product.getProduct().getId());
                productsIterator.remove();
            }
        }

        return productsId;
    }

    public void deleteAll(ProductRequestWrapper requestWrapper) {
        List<UUID> productsId = removeAllRequests(requestWrapper.getProductRequestList1());
        productsId.addAll(removeAllRequests(requestWrapper.getProductRequestList2()));
        productsId.addAll(removeAllRequests(requestWrapper.getProductRequestList3()));

        productRepository.deleteAllById(productsId);
    }

    private List<UUID> removeAllRequests(List<ProductRequest> products) {
        List<UUID> productsId = new ArrayList<>();

        ListIterator<ProductRequest> productsIterator = products.listIterator();
        while (productsIterator.hasNext()) {
            ProductRequest productReport = productsIterator.next();
            productsId.add(productReport.getProduct().getId());
            productsIterator.remove();
        }

        return productsId;
    }
}
