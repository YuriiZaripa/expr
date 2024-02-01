package ua.okwine.productexpirationdate.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class SupplierDTO {

    private UUID supplierId;
    private String supplierName;
    private String returnCondition;
    private int advanceNotice;
    private int discount;
}
