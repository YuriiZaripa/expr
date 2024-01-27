package ua.okwine.productexpirationdate.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.okwine.productexpirationdate.entity.Supplier;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuppliersByReturnConditionTypeDTO {
    private List<Supplier> exchange;
    private List<Supplier> writeOff;
    private List<Supplier> regular;
}
