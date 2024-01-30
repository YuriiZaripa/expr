package ua.okwine.productexpirationdate.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.rest.dto.ProductDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithReportedDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithSupplierIdDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SupplierMapper.class})
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    Product toProduct(ProductDTO productDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    Product toProduct(ProductWithSupplierIdDTO productDto);


    @Mapping(target = "supplier", source = "product.supplier.supplierName")
    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductDTOList(List<Product> products);

    @Mapping(target = "supplier", source = "product.supplier.supplierName")
    ProductWithReportedDTO toProductWithReportedDTO(Product product);

    List<ProductWithReportedDTO> toProductWithReportedDTOList(List<Product> products);

    List<Product> toProductWithIdList(List<ProductDTO> products);
}
