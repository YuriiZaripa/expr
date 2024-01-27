package ua.okwine.productexpirationdate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplierDTO {

    private UUID supplierId;
    private String supplierName;
    private String returnCondition;
    private int advanceNotice;
    private int discount;

}
