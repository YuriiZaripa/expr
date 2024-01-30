package ua.okwine.productexpirationdate.rest.dto.requestWrappers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestWrapper {

    private List<ProductRequest> productRequestList1;
    private List<ProductRequest> productRequestList2;
    private List<ProductRequest> productRequestList3;

}
