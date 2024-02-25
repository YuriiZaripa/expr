package ua.okwine.productexpirationdate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.entity.Sku;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.exceptions.NotExistingSupplierId;
import ua.okwine.productexpirationdate.repository.SkuRepository;
import ua.okwine.productexpirationdate.repository.SupplierRepository;
import ua.okwine.productexpirationdate.rest.dto.SkuDTO;
import ua.okwine.productexpirationdate.rest.dto.SkuWithSupplierIdDTO;
import ua.okwine.productexpirationdate.rest.dto.mapper.SkuMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkuService {

    private final SupplierRepository supplierRepository;
    private final SkuRepository skuRepository;
    private final SkuMapper skuMapper;

    public SkuDTO create(SkuWithSupplierIdDTO skuWithSupplierIdDTO) {
        Sku sku = skuMapper.toSku(skuWithSupplierIdDTO);
        Supplier supplier = supplierRepository.findById(skuWithSupplierIdDTO.getSupplier())
                .orElseThrow(() -> new NotExistingSupplierId(skuWithSupplierIdDTO.getSupplier()));
        sku.setSupplier(supplier);
        Sku saveSku = skuRepository.save(sku);

        return skuMapper.toSkuDTO(skuRepository.save(saveSku));
    }

    public SkuDTO update(UUID id, SkuWithSupplierIdDTO skuWithSupplierIdDTO) {
        Sku existingSku = skuRepository.findById(id).orElseThrow();
        Supplier supplier = supplierRepository.findById(skuWithSupplierIdDTO.getSupplier()).
                orElseThrow(() -> new NotExistingSupplierId(skuWithSupplierIdDTO.getSupplier()));
        skuMapper.updateSkuFromDTO(existingSku, skuWithSupplierIdDTO);
        existingSku.setSupplier(supplier);

        return skuMapper.toSkuDTO(skuRepository.save(existingSku));
    }

    public Optional<SkuDTO> findById(UUID id) {
        return skuRepository.findById(id).map(skuMapper::toSkuDTO);
    }

    public List<SkuDTO> findAll() {
        return skuMapper.toListSkuDTO(skuRepository.findAll());
    }

    public void deleteById(UUID id) {
        skuRepository.deleteById(id);
    }
}