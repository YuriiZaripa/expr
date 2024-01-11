package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Supplier;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ImportService {

    private final ProductService productService;
    private final SupplierService supplierService;

    public String saveFile(Model model, MultipartFile file) throws  IOException{
        String uploadDirectoryPath = "src/main/resources/temp_files";
        String fullPath = "src/main/resources/temp_files/" + file.getOriginalFilename();

        Path fileNameAndPath = Paths.get(uploadDirectoryPath, file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        model.addAttribute("msg", "Файл " + file.getOriginalFilename() +
                " успешно загружен.");
        return fullPath;
    }

    public void saveProductFromExcel(Model model, String path) {
        Map<String, Supplier> providerMap = supplierService.findAllByName();
        List<Product> productList = excelProductImport(model, path, providerMap);

        productService.saveAll(productList);
    }

    public  List<Product> excelProductImport(Model model, String excelFilePath, Map<String, Supplier> providerMap) {
        List <Product> productList = new ArrayList<>();
        int rowCounter = 1;

        try(FileInputStream inputStream = new FileInputStream(excelFilePath)) {
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
                Supplier supplier = providerMap.get(nextRow.getCell(6).getStringCellValue());

                productList.add(new Product(vendorCode, barCode, productName,
                        produced, expirationDate, supplier));
            }
        } catch (Exception e) {
            log.error("Import file " + excelFilePath + " was stopped.", e);
            model.addAttribute("msg", "Обработка файла \"" + excelFilePath +
                    "\" была прервана из-за несотвецтвия формата, или пустой ячейки в " + rowCounter + " рядке документа.");
        }

        return  productList;
    }

    public void saveSupplierFromExcel(Model model, String path) {
        List<Supplier> supplierList = excelSupplierImport(model, path);
        supplierService.saveAll(supplierList);
    }

    public static List<Supplier> excelSupplierImport(Model model, String excelFilePath) {
        List <Supplier> supplierList = new ArrayList<>();
        int rowCounter = 1;

        try(FileInputStream inputStream = new FileInputStream(excelFilePath)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet excelSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = excelSheet.iterator();

            rowIterator.next();
            while (rowIterator.hasNext()) {
                rowCounter++;
                Row nextRow = rowIterator.next();

                String providerName = nextRow.getCell(0).getStringCellValue();
                String returnCondition = nextRow.getCell(1).getStringCellValue();
                int advanceNotice = (int)nextRow.getCell(2).getNumericCellValue();
                int discount = (int)nextRow.getCell(3).getNumericCellValue();

                supplierList.add(new Supplier(providerName, returnCondition, advanceNotice, discount));
            }

        } catch (Exception e) {
            log.error("Import file " + excelFilePath + " was stopped.", e);
            model.addAttribute("msg", "Обработка файла \"" + excelFilePath +
                    "\" была прервана в " + rowCounter + " рядке документа из-за пустой или несответствующего формата ячейки.");
        }

        return supplierList;
    }
}
