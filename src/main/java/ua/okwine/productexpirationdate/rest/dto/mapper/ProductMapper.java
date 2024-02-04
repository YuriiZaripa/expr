package ua.okwine.productexpirationdate.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.rest.dto.ProductDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithReportedDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithSkuIdDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SkuMapper.class})
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductDTO productDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sku", ignore = true)
    Product toProduct(ProductWithSkuIdDTO productDto);
    
    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductDTOList(List<Product> products);

    ProductWithReportedDTO toProductWithReportedDTO(Product product);

    List<ProductWithReportedDTO> toProductWithReportedDTOList(List<Product> products);
}
