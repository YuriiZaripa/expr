package ua.okwine.productexpirationdate.service;

import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.dao.SupplierRepository;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.excelImport.ExcelImport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public void saveFromExcel(String path) {
        List<Supplier> supplierList = ExcelImport.excelProviderImport(path);
        supplierRepository.saveAll(supplierList);
    }

    public Supplier findById(int id) {
        return supplierRepository.getById(id);
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAllByOrderBySupplierNameAsc();
    }

    public Map<String, Supplier> findAllByName() {
        List<Supplier> suppliers = findAll();
        Map<String, Supplier> suppliersByName = new HashMap<>();

        for(Supplier supplier : suppliers) {
            suppliersByName.put(supplier.getSupplierName(), supplier);
        }

        return suppliersByName;
    }

    public void deleteById(int id) {
        supplierRepository.deleteById(id);
    }
}
