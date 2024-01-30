package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.exceptions.NotExistingOrEmptySupplier;
import ua.okwine.productexpirationdate.rest.dto.ProductDTO;
import ua.okwine.productexpirationdate.rest.dto.SupplierDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ImportService {

    private final ProductService productService;
    private final SupplierService supplierService;

    public String saveFile(MultipartFile file) throws IOException {
        String uploadDirectoryPath = "src/main/resources/temp_files";
        String fullPath = "src/main/resources/temp_files/" + file.getOriginalFilename();

        Path fileNameAndPath = Paths.get(uploadDirectoryPath, file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        return fullPath;
    }

    public List<ProductDTO> saveProductFromExcel(String path) {
        Map<String, Supplier> suppliersMap = supplierService.findAllByName();

        var products = excelProductImport(path, suppliersMap);
        return productService.saveAll(products);
    }

    public List<Product> excelProductImport(String excelFilePath, Map<String, Supplier> suppliersMap) {
        List<Product> productList = new ArrayList<>();
        int rowCounter = 1;

        try (FileInputStream inputStream = new FileInputStream(excelFilePath)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet excelSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = excelSheet.iterator();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date tempDate;

            rowIterator.next();
            while (rowIterator.hasNext()) {
                rowCounter++;
                Row nextRow = rowIterator.next();
                tempDate = null;
                LocalDate produced = null;

                String vendorCode = nextRow.getCell(1).getStringCellValue();
                String barCode = nextRow.getCell(2).getStringCellValue().trim();
                String productName = nextRow.getCell(3).getStringCellValue();
                tempDate = nextRow.getCell(4).getDateCellValue();
                if (tempDate != null) {
                    produced = LocalDate.parse(dateFormat.format(tempDate));
                }

                tempDate = nextRow.getCell(5).getDateCellValue();
                LocalDate expirationDate = LocalDate.parse(dateFormat.format(tempDate));
                Supplier supplier = suppliersMap.get(nextRow.getCell(6).getStringCellValue());

                if (supplier == null) {
                    throw new NotExistingOrEmptySupplier(rowCounter);
                }

                productList.add(new Product(vendorCode, barCode, productName,
                        produced, expirationDate, supplier));
            }
        } catch (IOException e) {
            log.error("Import file " + excelFilePath + " was stopped by row " + rowCounter + ". ", e);
            throw new RuntimeException("Import file " + excelFilePath + " was stopped by row " + rowCounter + ". ", e);
        }

        return productList;
    }

    public List<SupplierDTO> saveSupplierFromExcel(String path) {
        List<Supplier> supplierList = excelSupplierImport(path);
        return supplierService.saveAll(supplierList);
    }

    public static List<Supplier> excelSupplierImport(String excelFilePath) {
        List<Supplier> supplierList = new ArrayList<>();
        int rowCounter = 1;

        try (FileInputStream inputStream = new FileInputStream(excelFilePath)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet excelSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = excelSheet.iterator();

            rowIterator.next();
            while (rowIterator.hasNext()) {
                rowCounter++;
                Row nextRow = rowIterator.next();

                String supplierName = nextRow.getCell(0).getStringCellValue();
                String returnCondition = nextRow.getCell(1).getStringCellValue();
                int advanceNotice = (int) nextRow.getCell(2).getNumericCellValue();
                int discount = (int) nextRow.getCell(3).getNumericCellValue();

                supplierList.add(new Supplier(supplierName, returnCondition, advanceNotice, discount));
            }

        } catch (Exception e) {
            log.error("Import file " + excelFilePath + " was stopped by row " + rowCounter + ". ", e);
            throw new RuntimeException("Import file " + excelFilePath + " was stopped by row " + rowCounter + ". ", e);
        }

        return supplierList;
    }
}
