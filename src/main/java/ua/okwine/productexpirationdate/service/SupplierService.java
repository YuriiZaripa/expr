package ua.okwine.productexpirationdate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.dao.SupplierRepository;
import ua.okwine.productexpirationdate.dto.SupplierDTO;
import ua.okwine.productexpirationdate.dto.mapper.SupplierMapper;
import ua.okwine.productexpirationdate.entity.Supplier;

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
        Supplier savaEntityToDB = supplierRepository.save(supplierToEntity);

        return supplierMapper.mapToSupplierDTO(savaEntityToDB);
    }

    public List<SupplierDTO> saveAll(List<SupplierDTO> supplierDTOList) {
        List<Supplier> supplierListEntity = supplierMapper.mapToListSupplier(supplierDTOList);
        List<Supplier> savedListEntityToDB = supplierRepository.saveAll(supplierListEntity);

        return supplierMapper.mapToListSupplierDTO(savedListEntityToDB);
   }

    public List<Supplier> findAllActive() {
        return supplierRepository.findByIsActiveTrueOrderBySupplierName();
    }

    public List<Supplier> findAllWithNotActive() {
        return supplierRepository.findAllByOrderBySupplierNameAsc();
    }
    public SupplierDTO findById(UUID id) {

        return supplierMapper.mapToSupplierDTO(supplierRepository.getById(id));
    }

    public Map<String, Supplier> findAllByName() {
        List<Supplier> suppliers = findAllActive();
        Map<String, Supplier> suppliersByName = new HashMap<>();

        for(Supplier supplier : suppliers) {
            suppliersByName.put(supplier.getSupplierName(), supplier);
        }

        return suppliersByName;
    }

    public List<SupplierDTO> findAll() {
        List<SupplierDTO> listSupplierDTO = supplierMapper.mapToListSupplierDTO(supplierRepository.findAllByOrderBySupplierNameAsc());

        return listSupplierDTO;
    }

    public void deleteById(UUID id) {
        supplierRepository.deleteById(id);
    }
}
