package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.dao.ProductRepository;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Supplier;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierService supplierService;

    public List<Product> findAllNotReported() {
        return productRepository.findByIsReportedFalse();
    }

    public List<Product> findAllWithReported() {
        return productRepository.findAll();
    }

    public List<Product> findAllWithEmptyImage() {
        return productRepository.findByImageIsNull();
    }

    public Product findById(UUID id) {
        return productRepository.getById(id);
    }

    public List<Product> findAllNotReportedByAdvanceNotice(String advanceNotice) {
        return productRepository.findAllNotReportedByAdvanceNotice(advanceNotice);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }

    public void deleteAllById(List<UUID> id) { productRepository.deleteAllById(id); }

    public void updateAll(List<Product> products) {
        productRepository.saveAll(products);
    }
}
