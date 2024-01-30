package ua.okwine.productexpirationdate.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ProductDTO {

        private UUID id;
        private String vendorCode;
        private String barCode;
        private String productName;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate produced;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate expirationDate;
        private String supplier;
        private String image;
}
