package ua.okwine.productexpirationdate.rest.dto.parsing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OkwineResponse {

    private int statusCode;
    private OkwineResponsePageDTO data;
}
