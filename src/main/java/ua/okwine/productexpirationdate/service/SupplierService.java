package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.dao.SupplierRepository;
import ua.okwine.productexpirationdate.entity.Supplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public void saveAll(List<Supplier> suppliers) {
        supplierRepository.saveAll(suppliers);
    }

    public Supplier findById(UUID id) {
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

    public void deleteById(UUID id) {
        supplierRepository.deleteById(id);
    }
}
