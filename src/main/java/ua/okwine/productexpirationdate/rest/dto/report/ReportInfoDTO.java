package ua.okwine.productexpirationdate.rest.dto.report;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ua.okwine.productexpirationdate.rest.dto.ProductWithSupplierDTO;

import java.util.List;

@Getter
@Setter
@Builder
public class ReportInfoDTO {

    private List<ProductWithSupplierDTO> exchange;
    private List<ProductWithSupplierDTO> writeOff;
    private List<ProductWithSupplierDTO> regular;

}
