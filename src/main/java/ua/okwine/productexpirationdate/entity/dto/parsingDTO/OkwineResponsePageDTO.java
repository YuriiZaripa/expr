package ua.okwine.productexpirationdate.entity.dto.parsingDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OkwineResponsePageDTO {

    private int maxPage;
    private List<OkwineResponseProductDTO> data;
}
