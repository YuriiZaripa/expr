package ua.okwine.productexpirationdate.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SupplierWithProductsDTO {

    private UUID supplierId;
    private String supplierName;
    private String returnCondition;
    private int advanceNotice;
    private int discount;
    private boolean isActive;
    private List<ProductDTO> products;
}
