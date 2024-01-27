package ua.okwine.productexpirationdate.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SuppliersByReturnConditionTypeDTO {
    private List<SupplierDTO> exchange;
    private List<SupplierDTO> writeOff;
    private List<SupplierDTO> regular;
}
