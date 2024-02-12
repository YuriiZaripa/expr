package ua.okwine.productexpirationdate.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.okwine.productexpirationdate.rest.dto.report.ReportInfoDTO;
import ua.okwine.productexpirationdate.service.ReportService;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/daily")
    public ReportInfoDTO findAllRoDailyReport() {
        return reportService.findAllToDaileReport();
    }
}
