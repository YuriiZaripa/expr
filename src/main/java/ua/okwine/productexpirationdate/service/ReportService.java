package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.repository.ProductRepository;
import ua.okwine.productexpirationdate.rest.dto.ProductWithSupplierDTO;
import ua.okwine.productexpirationdate.rest.dto.mapper.ProductMapper;
import ua.okwine.productexpirationdate.rest.dto.report.ReportInfoDTO;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ReportInfoDTO findAllToDaileReport() {
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
}
