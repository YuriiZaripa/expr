package ua.okwine.productexpirationdate.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyReportDTO {

    private UUID id;
    private UUID product;
    private double quantity;
    private String comment;
}
