package ua.okwine.productexpirationdate.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.service.ProductService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping()
    public Product saveProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        productService.deleteById(id);
    }
}
