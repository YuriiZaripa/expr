package ua.okwine.productexpirationdate.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
public class SupplierDTO {

    private UUID supplierId;
    private String supplierName;
    private String returnCondition;
    private int advanceNotice;
    private int discount;

}
