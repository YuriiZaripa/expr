package ua.okwine.productexpirationdate.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.rest.dto.SupplierDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface SupplierMapper {

    @Mapping(target = "supplierId", ignore = true)
    Supplier toSupplier(SupplierDTO supplierDTO);

    SupplierDTO toSupplierDTO(Supplier supplier);

    List<SupplierDTO> toListSupplierDTO(List<Supplier> supplierList);
}
