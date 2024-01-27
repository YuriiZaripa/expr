package ua.okwine.productexpirationdate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.dao.SupplierRepository;
import ua.okwine.productexpirationdate.dto.SupplierDTO;
import ua.okwine.productexpirationdate.dto.mapper.SupplierMapper;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.entity.dto.SuppliersByReturnConditionTypeDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    public SupplierDTO save(SupplierDTO supplierDTO) {
        Supplier supplierToEntity = supplierMapper.mapToSupplier(supplierDTO);
        Supplier savedSupplierEntity = supplierRepository.save(supplierToEntity);

        return supplierMapper.mapToSupplierDTO(savedSupplierEntity);
    }

    public List<SupplierDTO> saveAll(List<SupplierDTO> supplierDTOList) {
        List<Supplier> supplierEntities = supplierMapper.mapToListSupplier(supplierDTOList);
        List<Supplier> savedListEntityToDB = supplierRepository.saveAll(supplierEntities);

        return supplierMapper.mapToListSupplierDTO(savedListEntityToDB);
    }

    public List<SupplierDTO> findAllActive() {

        return supplierMapper.mapToListSupplierDTO(supplierRepository.findByIsActiveTrueOrderBySupplierName());
    }

    public List<SupplierDTO> findAllWithNotActive() {

        return supplierMapper.mapToListSupplierDTO(supplierRepository.findAllByOrderBySupplierNameAsc());
    }

    public SupplierDTO findById(UUID id) {

        return supplierMapper.mapToSupplierDTO(supplierRepository.getById(id));
    }

    public Map<String, Supplier> findAllByName() {
        List<Supplier> suppliers = supplierRepository.findByIsActiveTrueOrderBySupplierName();
        Map<String, Supplier> suppliersByName = new HashMap<>();

        for (Supplier supplier : suppliers) {
            suppliersByName.put(supplier.getSupplierName(), supplier);
        }

        return suppliersByName;
    }

    public List<SupplierDTO> findAll() {

        return supplierMapper.mapToListSupplierDTO(supplierRepository.findAllByOrderBySupplierNameAsc());
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
