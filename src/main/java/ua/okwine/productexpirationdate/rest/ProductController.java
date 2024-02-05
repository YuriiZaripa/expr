package ua.okwine.productexpirationdate.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.okwine.productexpirationdate.rest.dto.ProductDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithReportedDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithSkuIdDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithSupplierDTO;
import ua.okwine.productexpirationdate.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductWithSupplierDTO> findAllNotReported() {
        return productService.findAllNotReported();
    }

    @GetMapping("/forAllTime")
    public List<ProductWithReportedDTO> findAllWithReported() {
        return productService.findAllWithReported();
    }

    @PostMapping()
    public ProductDTO saveProduct(@RequestBody ProductWithSkuIdDTO product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        productService.deleteById(id);
    }
}
