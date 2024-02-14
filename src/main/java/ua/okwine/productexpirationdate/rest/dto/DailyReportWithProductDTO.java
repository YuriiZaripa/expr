package ua.okwine.productexpirationdate.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class DailyReportWithProductDTO {

    private UUID id;
    private ProductDTO product;
    private double quantity;
    private LocalDate created;
    private String comment;
}
