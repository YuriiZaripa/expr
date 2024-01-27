package ua.okwine.productexpirationdate.entity.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.okwine.productexpirationdate.entity.Supplier;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    @Mapping(target = "supplierId", ignore = true)
    Supplier mapToSupplier(SupplierDTO supplierDTO);

    SupplierDTO mapToSupplierDTO(Supplier supplier);

    List<SupplierDTO> mapToListSupplierDTO(List<Supplier> supplierList);

    List<Supplier> mapToListSupplier(List<SupplierDTO> supplierListDTO);

}
