package ua.okwine.productexpirationdate.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.okwine.productexpirationdate.service.ImportService;

import java.io.IOException;

@Controller
@RequestMapping("/import")
@AllArgsConstructor
@Slf4j
public class ImportController {

    private final ImportService importService;

    @GetMapping("/importForm")
    public String getFormToImport() {
        return "import";
    }

    @GetMapping("/productUploadForm")
    public String getProviderUploadForm() {
        return "products/productUploadForm";
    }

    @PostMapping("/importProductFromExcel")
    public String productExcelUpload(Model model, @RequestParam("file") MultipartFile file) {
        String fullPath = null;
        try {
            fullPath = importService.saveFile(model, file);
        } catch (IOException e) {
            log.error("Import file " + file.getOriginalFilename() + " was stopped.", e);
            model.addAttribute("msg", "Файл \"" + file.getOriginalFilename() +
                    "\" НЕ УДАЛОСЬ ИМПОРТИРОВАТЬ!");

            return "suppliers/importResult";
        }

        importService.saveProductFromExcel(model, fullPath);
        return "suppliers/importResult";
    }

    @GetMapping("/supplierUploadForm")
    public String getUploadForm() {
        return "suppliers/supplierUploadForm";
    }

    @PostMapping("/importSuppliersFromExcel")
    public String supplierExcelUpload(Model model, @RequestParam("file") MultipartFile file) {
        String fullPath = null;
        try {
            fullPath = importService.saveFile(model, file);
        } catch (IOException e) {
            log.error("Import file " + file.getOriginalFilename() + " was stopped.", e);
            model.addAttribute("msg", "Файл \"" + file.getOriginalFilename() +
                    "\" НЕ УДАЛОСЬ ИМПОРТИРОВАТЬ!");

            return "suppliers/importResult";
        }

        importService.saveSupplierFromExcel(model, fullPath);
        return "suppliers/importResult";
    }
}
