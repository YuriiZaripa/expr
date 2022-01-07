package ua.okwine.productexpirationdate.service;

import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.dao.ProductRepository;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.excelImport.ExcelImport;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequest;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequestWrapper;

import java.util.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierService supplierService;

    public ProductService(ProductRepository productRepository, SupplierService supplierService) {
        this.productRepository = productRepository;
        this.supplierService = supplierService;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Supplier> findAllSuppliers() {
        return supplierService.findAll();
    }

    public Product findById(UUID id) {
        return productRepository.getById(id);
    }

    public ProductRequestWrapper findAllToDaileReport() {
        List<Product> products1 = productRepository.findProductForDailyReportByType("не забирают возвраты");
        List<Product> products2 = productRepository.findProductForDailyReportByType("физобмен");
        List<Product> products3 = productRepository.findProductForDailyReportByType("");

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

    public void save(Product product) {
        productRepository.save(product);
    }

    public void saveFromExcel(String path) {
        Map<String, Supplier> providerMap = supplierService.findAllByName();
        List<Product> productList = ExcelImport.excelProductImport(path, providerMap);

        productRepository.saveAll(productList);
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }

    public void deleteAll(ProductRequestWrapper requestWrapper) {
        List<UUID> productsId = removeAllRequests(requestWrapper.getProductRequestList1());
        productsId.addAll(removeAllRequests(requestWrapper.getProductRequestList2()));
        productsId.addAll(removeAllRequests(requestWrapper.getProductRequestList3()));

        productRepository.deleteAllById(productsId);
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
        while(productsIterator.hasNext()) {
            ProductRequest product = productsIterator.next();

            if(product.getQuantity() == 0) {
                productsId.add(product.getId());
                productsIterator.remove();
            }
        }

        return  productsId;
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
