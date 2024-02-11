package ua.okwine.productexpirationdate.rest.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.okwine.productexpirationdate.rest.dto.ProductDTO;
import ua.okwine.productexpirationdate.rest.dto.requestWrappers.ProductRequest;

import java.util.List;

@Getter
@Setter
@Builder
public class ReportInfoDTO {

    private List<ProductDTO> exchange;
    private List<ProductDTO> writeOff;
    private List<ProductDTO> regular;

}
