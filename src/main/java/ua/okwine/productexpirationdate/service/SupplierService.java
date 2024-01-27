package ua.okwine.productexpirationdate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.dao.SupplierRepository;
import ua.okwine.productexpirationdate.entity.dto.SupplierDTO;
import ua.okwine.productexpirationdate.entity.dto.SupplierMapper;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.entity.dto.SuppliersByReturnConditionTypeDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    public SupplierDTO save(SupplierDTO supplierDTO) {
        Supplier supplierEntity = supplierMapper.mapToSupplier(supplierDTO);
        Supplier savedSupplier = supplierRepository.save(supplierEntity);

        return supplierMapper.mapToSupplierDTO(savedSupplier);
    }

    public List<SupplierDTO> saveAll(List<SupplierDTO> supplierDTOList) {
        List<Supplier> supplierEntities = supplierMapper.mapToListSupplier(supplierDTOList);
        List<Supplier> savedSuppliers = supplierRepository.saveAll(supplierEntities);

        return supplierMapper.mapToListSupplierDTO(savedSuppliers);
    }

    public List<SupplierDTO> findAllActive() {
        return supplierMapper.mapToListSupplierDTO(
                supplierRepository.findByIsActiveTrueOrderBySupplierName());
    }

    public List<SupplierDTO> findAllWithNotActive() {
        return supplierMapper.mapToListSupplierDTO(
                supplierRepository.findAllByOrderBySupplierNameAsc());
    }

    public Optional<SupplierDTO> findById(UUID id) {
        return supplierRepository.findById(id).map(supplierMapper::mapToSupplierDTO);
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
        List<Supplier> exchangeNotice = supplierRepository
                .findByReturnConditionAndIsActiveTrueOrderBySupplierName("не забирают возвраты");
        List<Supplier> writeOffNotice = supplierRepository
                .findByReturnConditionAndIsActiveTrueOrderBySupplierName("физобмен");
        List<Supplier> regularNotice = supplierRepository
                .findByReturnConditionAndIsActiveTrueOrderBySupplierName("");

        return SuppliersByReturnConditionTypeDTO.builder()
                .exchange(supplierMapper.mapToListSupplierDTO(exchangeNotice))
                .writeOff(supplierMapper.mapToListSupplierDTO(writeOffNotice))
                .regular(supplierMapper.mapToListSupplierDTO(regularNotice))
                .build();
    }
}
