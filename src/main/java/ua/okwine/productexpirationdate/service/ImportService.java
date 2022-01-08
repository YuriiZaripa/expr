package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
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
import java.util.*;

@Service
@AllArgsConstructor
public class ImportService {

    private final ProductService productService;
    private final SupplierService supplierService;

    public void saveProductFromExcel(String path) {
        Map<String, Supplier> providerMap = supplierService.findAllByName();
        List<Product> productList = excelProductImport(path, providerMap);

        productService.saveAll(productList);
    }

    public void saveSupplierFromExcel(String path) {
        List<Supplier> supplierList = excelSupplierImport(path);
        supplierService.saveAll(supplierList);
    }

    public  List<Product> excelProductImport(String excelFilePath, Map<String, Supplier> providerMap) {
        List <Product> productList = new ArrayList<>();

        try(FileInputStream inputStream = new FileInputStream(excelFilePath)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet excelSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = excelSheet.iterator();
            int rowCounter = 1;

            rowIterator.next();
            while (rowIterator.hasNext()) {
                rowCounter++;
                Row nextRow = rowIterator.next();

                String vendorCode = nextRow.getCell(1).getStringCellValue();
                String barCode = nextRow.getCell(2).getStringCellValue();
                String productName = nextRow.getCell(3).getStringCellValue();
                Date produced = nextRow.getCell(4).getDateCellValue();
                Date expirationDate = nextRow.getCell(5).getDateCellValue();;
                Supplier supplier = providerMap.get(nextRow.getCell(6).getStringCellValue());

                productList.add(new Product(vendorCode, barCode, productName,
                        produced, expirationDate, supplier));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  productList;
    }

    public static List<Supplier> excelSupplierImport(String excelFilePath) {

        List <Supplier> supplierList = new ArrayList<>();

        try(FileInputStream inputStream = new FileInputStream(excelFilePath)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet excelSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = excelSheet.iterator();

            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();

                String providerName = nextRow.getCell(0).getStringCellValue();
                String returnCondition = nextRow.getCell(1).getStringCellValue();
                int advanceNotice = (int)nextRow.getCell(2).getNumericCellValue();
                int discount = (int)nextRow.getCell(3).getNumericCellValue();

                supplierList.add(new Supplier(providerName, returnCondition, advanceNotice, discount));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return supplierList;
    }

    public String saveFile(Model model, MultipartFile file) {
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

        return fullPath;
    }
}
