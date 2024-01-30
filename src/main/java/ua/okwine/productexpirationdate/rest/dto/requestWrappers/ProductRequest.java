package ua.okwine.productexpirationdate.rest.dto.requestWrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.rest.dto.ProductDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private ProductDTO product;
    private double quantity = 0;

    public ProductRequest(ProductDTO product) {
        this.product = product;
    }
}
