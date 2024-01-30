package ua.okwine.productexpirationdate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.repository.SupplierRepository;
import ua.okwine.productexpirationdate.rest.dto.SupplierDTO;
import ua.okwine.productexpirationdate.rest.dto.SupplierWithProductsDTO;
import ua.okwine.productexpirationdate.rest.dto.SuppliersByReturnConditionTypeDTO;
import ua.okwine.productexpirationdate.rest.dto.mapper.SupplierMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    public SupplierDTO save(SupplierDTO supplierDTO) {
        Supplier supplierEntity = supplierMapper.toSupplier(supplierDTO);
        Supplier savedSupplier = supplierRepository.save(supplierEntity);

        return supplierMapper.toSupplierDTO(savedSupplier);
    }

    public List<SupplierDTO> findAllActive() {
        return supplierMapper.toListSupplierDTO(
                supplierRepository.findByIsActiveTrueOrderBySupplierName());
    }

    public List<SupplierDTO> findAllWithNotActive() {
        return supplierMapper.toListSupplierDTO(
                supplierRepository.findAllByOrderBySupplierNameAsc());
    }

    public Optional<SupplierDTO> findById(UUID id) {
        return supplierRepository.findById(id).map(supplierMapper::toSupplierDTO);
    }

    public Optional<SupplierWithProductsDTO> findByIdWithProducts(UUID id) {
        return supplierRepository.findById(id).map(supplierMapper::toSupplierWithProductsDTO);
    }

    public List<SupplierDTO> findAll() {
        return supplierMapper.toListSupplierDTO(supplierRepository.findAllByOrderBySupplierNameAsc());
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
                .exchange(supplierMapper.toListSupplierDTO(exchangeNotice))
                .writeOff(supplierMapper.toListSupplierDTO(writeOffNotice))
                .regular(supplierMapper.toListSupplierDTO(regularNotice))
                .build();
    }
}
