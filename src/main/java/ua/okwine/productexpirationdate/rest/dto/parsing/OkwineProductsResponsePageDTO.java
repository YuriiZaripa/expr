package ua.okwine.productexpirationdate.rest.dto.parsing;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OkwineProductsResponsePageDTO {

    private int maxPage;
    private List<OkwineProductsResponseSingleProductDTO> data;
}
