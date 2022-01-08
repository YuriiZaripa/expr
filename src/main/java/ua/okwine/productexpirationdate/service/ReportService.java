package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequest;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequestWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReportService {

    private final ProductService productService;

    public ProductRequestWrapper findAllToDaileReport() {
        List<Product> products1 = productService.findAllByAdvanceNotice("не забирают возвраты");
        List<Product> products2 = productService.findAllByAdvanceNotice("физобмен");
        List<Product> products3 = productService.findAllByAdvanceNotice("");

        ProductRequestWrapper productRequestWrapper = new ProductRequestWrapper(
                toProductRequest(products1),
                toProductRequest(products2),
                toProductRequest(products3)
        );

        return productRequestWrapper;
    }

    private List<ProductRequest> toProductRequest(List<Product> products) {
        List<ProductRequest> productRequests = new ArrayList<>();

        for (Product product : products) {
            productRequests.add(new ProductRequest(product));
        }

        return productRequests;
    }

    public void productsProcessing(ProductRequestWrapper requestWrapper) {
        List<UUID> productsId = removeZeroQuantity(requestWrapper.getProductRequestList1());
        productsId.addAll(removeZeroQuantity(requestWrapper.getProductRequestList2()));
        productsId.addAll(removeZeroQuantity(requestWrapper.getProductRequestList3()));

        productService.deleteAllById(productsId);
    }

    private List<UUID> removeZeroQuantity(List<ProductRequest> products) {
        List<UUID> productsId = new ArrayList<>();

        ListIterator<ProductRequest> productsIterator = products.listIterator();
        while(productsIterator.hasNext()) {
            ProductRequest product = productsIterator.next();

            if(product.getQuantity() == 0) {
                productsId.add(product.getId());
                productsIterator.remove();
            }
        }

        return  productsId;
    }

    public void deleteAll(ProductRequestWrapper requestWrapper) {
        List<UUID> productsId = removeAllRequests(requestWrapper.getProductRequestList1());
        productsId.addAll(removeAllRequests(requestWrapper.getProductRequestList2()));
        productsId.addAll(removeAllRequests(requestWrapper.getProductRequestList3()));

        productService.deleteAllById(productsId);
    }



    private List<UUID> removeAllRequests(List<ProductRequest> products) {
        List<UUID> productsId = new ArrayList<>();

        ListIterator<ProductRequest> productsIterator = products.listIterator();
        while(productsIterator.hasNext()) {
            ProductRequest product = productsIterator.next();
            productsId.add(product.getId());
            productsIterator.remove();
        }

        return  productsId;
    }
}
