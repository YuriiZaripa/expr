package ua.okwine.productexpirationdate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Provider;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequest;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequestWrapper;
import ua.okwine.productexpirationdate.service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/main")
    public String startPage() {
        return "startpage";
    }

    @GetMapping("/newExpDateForm")
    public String showFormForAdd(Model model) {
        List<Provider> providers = productService.findAllProviders();
        model.addAttribute("providers", providers);
        model.addAttribute("product", new Product());

        return "products/new-expiration-date-form";
    }

    @GetMapping("/list-products")
    public String getAll(Model model) {
        List<Product> productList = productService.findAll();

        model.addAttribute("products", productList);

        return "products/list-products";
    }

    @GetMapping("/productUploadForm")
    public String providerUploadForm() {
        return "products/product-upload-form";
    }

    @PostMapping("/importProductFromExcel")
    public String importExcel(Model model, @RequestParam("file") MultipartFile file) {
        String uploadDirectory = "src/main/resources/temp_files";
        String fullPath = "src/main/resources/temp_files/" + file.getOriginalFilename();

        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("msg", "Файл " + file.getOriginalFilename() +
                    " НЕ УДАЛОСЬ ИМПОРТИРОВАТЬ!");

            return "providers/import-result";
        }
        model.addAttribute("msg", "Файл " + file.getOriginalFilename() +
                " успешно загружен.");

        productService.saveFromExcel(fullPath);
        return "providers/import-result";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);

        return "redirect:/products/newExpDateForm";
    }

    @GetMapping("/updateProductForm")
    public String updateProduct(@RequestParam("id") int id,
                                Model model) {
        Product product = productService.findById(id);
        List<Provider> providers = productService.findAllProviders();

        model.addAttribute("product", product);
        model.addAttribute("providers", providers);

        return "products/new-expiration-date-form";
    }

    @GetMapping("/settings")
    public String getSettings() {
        return "settings";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        productService.deleteById(id);

        return "redirect:/products/list-products";
    }

    @GetMapping("/dailyReport")
    public String getDailyReport(Model model) {

        model.addAttribute("products", productService.findAllToDaileReport());

        return "products/daily-report";
    }

    @PostMapping("/updateProducts")
    public String productsProcessing(
            @ModelAttribute("products") ProductRequestWrapper products) {
        productService.productsProcessing(products);

        return "products/report-result";
    }

    @PostMapping("/dailyReportDone")
    public String deleteProducts(@ModelAttribute("products") ProductRequestWrapper products) {
        productService.deleteAll(products);

        return "redirect:/products/main";
    }
}
