package ua.okwine.productexpirationdate.rest.dto.parsing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OkwineResiduesResponseDTO {

    private int statusCode;
    private OkwineDataResiduesResponseDTO data;
}
