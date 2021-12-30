package ua.okwine.productexpirationdate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.dao.ProductRepository;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Provider;
import ua.okwine.productexpirationdate.excelImport.ExcelImport;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequest;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequestWrapper;

import java.util.*;

@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final ProviderService providerService;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository, ProviderService providerService) {
        this.productRepository = productRepository;
        this.providerService = providerService;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Provider> findAllProviders() {
        return providerService.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.getById(id);
    }

    @Override
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

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void saveFromExcel(String path) {
        Map<String, Provider> providerMap = providerService.findAllByName();
        List<Product> productList = ExcelImport.excelProductImport(path, providerMap);

        productRepository.saveAll(productList);
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public void deleteAll(ProductRequestWrapper requestWrapper) {
        List<Integer> productsId = removeAllRequests(requestWrapper.getProductRequestList1());
        productsId.addAll(removeAllRequests(requestWrapper.getProductRequestList2()));
        productsId.addAll(removeAllRequests(requestWrapper.getProductRequestList3()));

        productRepository.deleteAllById(productsId);
    }

    @Override
    public void productsProcessing(ProductRequestWrapper requestWrapper) {
        List<Integer> productsId = removeZeroQuantity(requestWrapper.getProductRequestList1());
        productsId.addAll(removeZeroQuantity(requestWrapper.getProductRequestList2()));
        productsId.addAll(removeZeroQuantity(requestWrapper.getProductRequestList3()));

        productRepository.deleteAllById(productsId);
    }

    private List<Integer> removeZeroQuantity(List<ProductRequest> products) {
        List<Integer> productsId = new ArrayList<>();

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



    private List<Integer> removeAllRequests(List<ProductRequest> products) {
        List<Integer> productsId = new ArrayList<>();

        ListIterator<ProductRequest> productsIterator = products.listIterator();
        while(productsIterator.hasNext()) {
            ProductRequest product = productsIterator.next();
            productsId.add(product.getId());
            productsIterator.remove();
        }

        return  productsId;
    }

}
