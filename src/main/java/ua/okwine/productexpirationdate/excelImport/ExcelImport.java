package ua.okwine.productexpirationdate.excelImport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.format.annotation.DateTimeFormat;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Provider;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.FileInputStream;
import java.util.*;

public class ExcelImport {
    public static List<Provider> excelProviderImport(String excelFilePath) {
        List <Provider> providerList = new ArrayList<>();

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

                providerList.add(new Provider(providerName, returnCondition, advanceNotice, discount));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  providerList;
    }

    public static List<Product> excelProductImport(String excelFilePath, Map<String, Provider> providerMap) {
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
                Provider provider = providerMap.get(nextRow.getCell(6).getStringCellValue());

                productList.add(new Product(vendorCode, barCode, productName,
                        produced, expirationDate, provider));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  productList;
    }
}
