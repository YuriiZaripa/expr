package ua.okwine.productexpirationdate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.service.SupplierService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliersList")
    public String getAll(Model model) {

        model.addAttribute("suppliers", supplierService.findAll());

        return "suppliers/suppliersList";
    }

    @GetMapping("/showFormForAdd")
    public String getFormForAdd(Model model) {
        Supplier supplier = new Supplier();

        model.addAttribute("supplier", supplier);

        return "suppliers/newSupplierForm";
    }

    @PostMapping("/save")
    public String saveProvider(@ModelAttribute("provider") Supplier supplier) {
        supplierService.save(supplier);

        return "redirect:suppliersList";
    }

    @GetMapping("/supplierUploadForm")
    public String getUploadForm() {
        return "suppliers/supplierUploadForm";
    }

    @PostMapping("/importSuppliersFromExcel")
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


        supplierService.saveFromExcel(fullPath);
        return "suppliers/importResult";
    }

    @GetMapping("/updateSupplierForm")
    public String updateProvider(@RequestParam("supplierId") UUID id,
                                 Model model) {
        Supplier supplier = supplierService.findById(id);

        model.addAttribute("supplier", supplier);

        return "suppliers/newSupplierForm";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam("supplierId") UUID id) {
        supplierService.deleteById(id);

        return "redirect:suppliersList";
    }
}
