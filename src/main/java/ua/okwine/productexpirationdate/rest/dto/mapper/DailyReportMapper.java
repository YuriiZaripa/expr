package ua.okwine.productexpirationdate.rest.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.okwine.productexpirationdate.entity.DailyReport;
import ua.okwine.productexpirationdate.rest.dto.DailyReportDTO;
import ua.okwine.productexpirationdate.rest.dto.DailyReportWithProductDTO;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface DailyReportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    DailyReport toDailyReport(DailyReportDTO dailyReport);

    DailyReportWithProductDTO toDailyReportWithProductDTO(DailyReport dailyReport);
}
