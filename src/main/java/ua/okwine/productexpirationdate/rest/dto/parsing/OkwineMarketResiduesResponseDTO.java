package ua.okwine.productexpirationdate.rest.dto.parsing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OkwineMarketResiduesResponseDTO {

    private String _id;
    private int count;
    private OkwineMarketIdDTO market_id;
}
