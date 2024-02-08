package ua.okwine.productexpirationdate.rest.dto.parsing;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OkwineDataResiduesResponseDTO {

    private String pr_id;
    private List<OkwineMarketResiduesResponseDTO> markets;
}
