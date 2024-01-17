package ua.okwine.productexpirationdate.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequestWrapper;
import ua.okwine.productexpirationdate.service.ReportService;

@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportController {

    private  final ReportService reportService;

    @GetMapping("/dailyReport")
    public ProductRequestWrapper createDailyReport() {
        return reportService.findAllToDaileReport();
    }
}
