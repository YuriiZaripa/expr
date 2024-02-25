package ua.okwine.productexpirationdate.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class DailyReportWithProductAndSupplierDTO {

    private UUID id;
    private ProductWithSupplierDTO product;
    private double quantity;
    private LocalDate created;
    private String comment;
}
