package ua.okwine.productexpirationdate.service;

import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Provider;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequest;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequestWrapper;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    List<Provider> findAllProviders();

    Product findById(int id);

    ProductRequestWrapper findAllToDaileReport();

    void save(Product product);

    void saveFromExcel(String path);

    void deleteById(int id);

    public void deleteAll(ProductRequestWrapper requestWrapper);

    void productsProcessing(ProductRequestWrapper requestWrapper);

}
