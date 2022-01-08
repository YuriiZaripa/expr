package ua.okwine.productexpirationdate.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequestWrapper;
import ua.okwine.productexpirationdate.service.ProductService;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/main")
    public String getStartPage() {
        return "startPage";
    }

    @GetMapping("/newExpDateForm")
    public String getFormForAdd(Model model) {
        List<Supplier> suppliers = productService.findAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("product", new Product());

        return "products/newExpirationDateForm";
    }

    @GetMapping("/productsList")
    public String getAll(Model model) {
        List<Product> productList = productService.findAll();

        model.addAttribute("products", productList);

        return "products/productsList";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);

        return "redirect:/products/newExpDateForm";
    }

    @GetMapping("/updateProductForm")
    public String updateProduct(@RequestParam("id") UUID id,
                                Model model) {
        Product product = productService.findById(id);
        List<Supplier> suppliers = productService.findAllSuppliers();

        model.addAttribute("product", product);
        model.addAttribute("suppliers", suppliers);

        return "products/newExpirationDateForm";
    }


    @GetMapping("/delete")
    public String deleteById(@RequestParam("id") UUID id) {
        productService.deleteById(id);

        return "redirect:/products/productsList";
    }
}
