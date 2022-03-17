package ua.okwine.productexpirationdate.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.okwine.productexpirationdate.requestWrappers.ProductRequestWrapper;
import ua.okwine.productexpirationdate.service.ReportService;

@Controller
@RequestMapping("/report")
@AllArgsConstructor
public class ReportController {

    private  final ReportService reportService;

    @GetMapping("/dailyReport")
    public String getDailyReport(Model model) {

        model.addAttribute("products", reportService.findAllToDaileReport());

        return "reports/dailyReport";
    }

    @PostMapping("/updateProducts")
    public String processing(
            @ModelAttribute("products") ProductRequestWrapper products) {
        reportService.productsProcessing(products);

        return "reports/reportResult";
    }

    @PostMapping("/dailyReportDone")
    public String deleteProducts(@ModelAttribute("products") ProductRequestWrapper products) {
        reportService.deleteAll(products);

        return "redirect:/products/main";
    }
}
