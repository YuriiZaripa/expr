package ua.okwine.productexpirationdate.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.okwine.productexpirationdate.rest.dto.ProductDTO;
import ua.okwine.productexpirationdate.rest.dto.SupplierDTO;
import ua.okwine.productexpirationdate.service.ImportService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/import")
@AllArgsConstructor
@Slf4j
public class ImportController {

    private final ImportService importService;

    @PostMapping(value = "/importProductFromExcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<ProductDTO> productExcelUpload(@RequestParam("upload") MultipartFile file) {
        String fullPath = null;
        try {
            fullPath = importService.saveFile(file);
        } catch (IOException e) {
            log.error("Import file " + file.getOriginalFilename() + " was stopped.", e);

            throw new RuntimeException("Uploading " + file.getName() + "was filed!");
        }

        return importService.saveProductFromExcel(fullPath);
    }

    @PostMapping(value = "/importSuppliersFromExcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<SupplierDTO> supplierExcelUpload(@RequestPart(value = "upload") MultipartFile file) {
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