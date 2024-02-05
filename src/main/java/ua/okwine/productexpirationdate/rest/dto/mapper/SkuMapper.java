package ua.okwine.productexpirationdate.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Lazy;
import ua.okwine.productexpirationdate.entity.Sku;
import ua.okwine.productexpirationdate.rest.dto.SkuDTO;
import ua.okwine.productexpirationdate.rest.dto.SkuWithSupplierDTO;
import ua.okwine.productexpirationdate.rest.dto.SkuWithSupplierIdDTO;

@Mapper(componentModel = "spring", uses = {SupplierMapper.class})
public interface SkuMapper {

    @Mapping(target = "supplier", source = "sku.supplier.supplierName")
    SkuDTO toSkuDTO(Sku sku);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    Sku toSku(SkuDTO sku);

    SkuWithSupplierDTO toSkuWithSupplierDTO(Sku sku);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    Sku toSku(SkuWithSupplierIdDTO sku);
}