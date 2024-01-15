package ua.okwine.productexpirationdate.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.service.ImportService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/import")
@AllArgsConstructor
@Slf4j
public class ImportController {

    private final ImportService importService;

//    @GetMapping("/importForm")
//    public String getFormToImport() {
//        return "import";
//    }
//
//    @GetMapping("/productUploadForm")
//    public String getProviderUploadForm() {
//        return "products/productUploadForm";
//    }

    @PostMapping(value = "/importProductFromExcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Product> productExcelUpload(@RequestParam("upload") MultipartFile file) {
        String fullPath = null;
        try {
            fullPath = importService.saveFile( file);
        } catch (IOException e) {
            log.error("Import file " + file.getOriginalFilename() + " was stopped.", e);

            throw new RuntimeException("Uploading " + file.getName() + "was filed!");
        }

        return importService.saveProductFromExcel(fullPath);
    }

//    @GetMapping("/supplierUploadForm")
//    public String getUploadForm() {
//        return "suppliers/supplierUploadForm";
//    }

    @PostMapping(value ="/importSuppliersFromExcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Supplier> supplierExcelUpload(@RequestPart(value = "upload") MultipartFile file) {
        String fullPath = null;
        try {
            fullPath = importService.saveFile(file);
        } catch (IOException e) {
            log.error("Import file " + file.getOriginalFilename() + " was stopped.", e);
            throw new RuntimeException("Import file " + file.getOriginalFilename() + " was stopped.");
        }

        return importService.saveSupplierFromExcel(fullPath);
    }
}
