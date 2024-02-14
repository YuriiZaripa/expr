package ua.okwine.productexpirationdate.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.exceptions.NotExistingProductException;
import ua.okwine.productexpirationdate.repository.DailyReportRepository;
import ua.okwine.productexpirationdate.repository.ProductRepository;
import ua.okwine.productexpirationdate.rest.dto.DailyReportDTO;
import ua.okwine.productexpirationdate.rest.dto.DailyReportWithProductDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithSupplierDTO;
import ua.okwine.productexpirationdate.rest.dto.mapper.DailyReportMapper;
import ua.okwine.productexpirationdate.rest.dto.mapper.ProductMapper;
import ua.okwine.productexpirationdate.rest.dto.report.ReportInfoDTO;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {

    private final ProductRepository productRepository;
    private final DailyReportRepository dailyReportRepository;

    private final ProductMapper productMapper;
    private final DailyReportMapper dailyReportMapper;

    public ReportInfoDTO findAllDaileReport() {
        List<ProductWithSupplierDTO> writeOff = productMapper
                .toProductWithSupplierListDTO(productRepository.findAllNotReportedByAdvanceNotice("не забирают возвраты"));
        List<ProductWithSupplierDTO> exchange = productMapper
                .toProductWithSupplierListDTO(productRepository.findAllNotReportedByAdvanceNotice("физобмен"));
        List<ProductWithSupplierDTO> regular = productMapper
                .toProductWithSupplierListDTO(productRepository.findAllNotReportedByAdvanceNotice(""));

        ReportInfoDTO reportInfoDTO = ReportInfoDTO.builder()
                .exchange(exchange)
                .writeOff(writeOff)
                .regular(regular)
                .build();

        return reportInfoDTO;
    }

    @Transactional
    public DailyReportWithProductDTO saveDailyReport(DailyReportDTO dailyReport) {
        var dailyReportEntity = dailyReportMapper.toDailyReport(dailyReport);

        dailyReportEntity.setProduct(
                productRepository.findById(dailyReport.getProduct())
                        .orElseThrow(() -> new NotExistingProductException(dailyReport.getProduct()))
        );
        dailyReportEntity.getProduct().setReported(true);

        return dailyReportMapper.toDailyReportWithProductDTO(dailyReportRepository.save(dailyReportEntity));
    }
}
