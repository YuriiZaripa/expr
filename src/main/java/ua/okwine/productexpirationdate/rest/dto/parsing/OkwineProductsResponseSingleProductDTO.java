package ua.okwine.productexpirationdate.rest.dto.parsing;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OkwineProductsResponseSingleProductDTO {

    private List<String> images;
    private String utp;
    private String pr_id;
}
