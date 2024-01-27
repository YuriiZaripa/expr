package ua.okwine.productexpirationdate.entity.dto.parsing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OkwineResponseProductDTO {

    private List<String> images;
    private String utp;
}
