package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.dao.ProductRepository;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequest;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequestWrapper;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierService supplierService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Supplier> findAllSuppliers() {
        return supplierService.findAll();
    }

    public Product findById(UUID id) {
        return productRepository.getById(id);
    }

    public List<Product> findAllByAdvanceNotice(String advanceNotice) {
        return productRepository.findAllByAdvanceNotice(advanceNotice);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }

    public void deleteAllById(List<UUID> id) { productRepository.deleteAllById(id); }
}
