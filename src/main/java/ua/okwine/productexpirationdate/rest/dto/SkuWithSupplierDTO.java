package ua.okwine.productexpirationdate.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
public class SkuWithSupplierDTO {

    private UUID id;
    private String vendorCode;
    private Set<String> barCode;
    private String title;
    private String image;
    private SupplierDTO supplier;
    private Integer stockBalance;
}
