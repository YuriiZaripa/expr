package ua.okwine.productexpirationdate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequestWrapper;
import ua.okwine.productexpirationdate.service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

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

    @GetMapping("/productUploadForm")
    public String getProviderUploadForm() {
        return "products/productUploadForm";
    }

    @PostMapping("/importProductFromExcel")
    public String importExcel(Model model, @RequestParam("file") MultipartFile file) {
        String uploadDirectoryPath = "src/main/resources/temp_files";
        String fullPath = "src/main/resources/temp_files/" + file.getOriginalFilename();

        Path fileNameAndPath = Paths.get(uploadDirectoryPath, file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("msg", "Файл " + file.getOriginalFilename() +
                    " НЕ УДАЛОСЬ ИМПОРТИРОВАТЬ!");

            return "suppliers/importResult";
        }
        model.addAttribute("msg", "Файл " + file.getOriginalFilename() +
                " успешно загружен.");

        productService.saveFromExcel(fullPath);
        return "suppliers/importResult";
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
        List<Supplier> suppliers = productService.findAllSuppliers();

        model.addAttribute("product", product);
        model.addAttribute("suppliers", suppliers);

        return "products/newExpirationDateForm";
    }

    @GetMapping("/settings")
    public String getSettings() {
        return "settings";
    }


    @GetMapping("/delete")
    public String deleteById(@RequestParam("id") int id) {
        productService.deleteById(id);

        return "redirect:/products/productsList";
    }

    @GetMapping("/dailyReport")
    public String getDailyReport(Model model) {

        model.addAttribute("products", productService.findAllToDaileReport());

        return "products/dailyReport";
    }

    @PostMapping("/updateProducts")
    public String processing(
            @ModelAttribute("products") ProductRequestWrapper products) {
        productService.productsProcessing(products);

        return "products/reportResult";
    }

    @PostMapping("/dailyReportDone")
    public String deleteProducts(@ModelAttribute("products") ProductRequestWrapper products) {
        productService.deleteAll(products);

        return "redirect:/products/main";
    }
}
