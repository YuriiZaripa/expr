package ua.okwine.productexpirationdate.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ProductWithSupplierDTO {

    private UUID id;
    private SkuWithSupplierDTO sku;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate produced;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
}
