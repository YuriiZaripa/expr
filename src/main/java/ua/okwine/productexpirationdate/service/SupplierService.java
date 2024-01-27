package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.dao.SupplierRepository;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.entity.dto.SuppliersByReturnConditionTypeDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> saveAll(List<Supplier> suppliers) {
        return supplierRepository.saveAll(suppliers);
    }

    public Supplier findById(UUID id) {
        return supplierRepository.getById(id);
    }

    public List<Supplier> findAllActive() {
        return supplierRepository.findByIsActiveTrueOrderBySupplierName();
    }

    public List<Supplier> findAllWithNotActive() {
        return supplierRepository.findAllByOrderBySupplierNameAsc();
    }

    public Map<String, Supplier> findAllByName() {
        List<Supplier> suppliers = findAllActive();
        Map<String, Supplier> suppliersByName = new HashMap<>();

        for(Supplier supplier : suppliers) {
            suppliersByName.put(supplier.getSupplierName(), supplier);
        }

        return suppliersByName;
    }

    public void deleteById(UUID id) {
        supplierRepository.deleteById(id);
    }

    public SuppliersByReturnConditionTypeDTO findAllSeparatedByType() {
        List<Supplier> exchangeNotice = supplierRepository.findByReturnConditionAndIsActiveTrueOrderBySupplierName("не забирают возвраты");
        List<Supplier> writeOffNotice = supplierRepository.findByReturnConditionAndIsActiveTrueOrderBySupplierName("физобмен");
        List<Supplier> regularNotice = supplierRepository.findByReturnConditionAndIsActiveTrueOrderBySupplierName("");

        return new SuppliersByReturnConditionTypeDTO(exchangeNotice, writeOffNotice, regularNotice);
    }
}
