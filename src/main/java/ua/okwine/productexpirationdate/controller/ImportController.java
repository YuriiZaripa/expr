package ua.okwine.productexpirationdate.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.okwine.productexpirationdate.service.ImportService;

@Controller
@RequestMapping("/import")
@AllArgsConstructor
public class ImportController {

    private final ImportService importService;

    @PostMapping("/importProductFromExcel")
    public String productExcelUpload(Model model, @RequestParam("file") MultipartFile file) {
        String fullPath = importService.saveFile(model, file);

        importService.saveProductFromExcel(fullPath);
        return "suppliers/importResult";
    }

    @PostMapping("/importSuppliersFromExcel")
    public String supplierExcelUpload(Model model, @RequestParam("file") MultipartFile file) {
        String fullPath = importService.saveFile(model, file);

        importService.saveSupplierFromExcel(fullPath);
        return "suppliers/importResult";
    }
}
