package ua.okwine.productexpirationdate.entity.dto.parsingDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OkwineResponse {

    private int statusCode;
    private OkwineResponsePageDTO data;
}
