package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.exceptions.NotExistingSkuException;
import ua.okwine.productexpirationdate.repository.ProductRepository;
import ua.okwine.productexpirationdate.repository.SkuRepository;
import ua.okwine.productexpirationdate.repository.SupplierRepository;
import ua.okwine.productexpirationdate.rest.dto.ProductDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithReportedDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithSkuIdDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithSupplierDTO;
import ua.okwine.productexpirationdate.rest.dto.mapper.ProductMapper;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ProductService {

    private final SupplierRepository supplierRepository;
    private final SkuRepository skuRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductWithSupplierDTO> findAllNotReported() {
        return productMapper.toProductWithSupplierListDTO(productRepository.findByIsReportedFalse());
    }

    public List<ProductWithReportedDTO> findAllWithReported() {
        return productMapper.toProductWithReportedDTOList(productRepository.findAll());
    }

    public ProductDTO findById(UUID id) {
        return productMapper.toProductDTO(productRepository.getById(id));
    }

    public ProductDTO save(ProductWithSkuIdDTO product) {
        var productEntity = productMapper.toProduct(product);
        productEntity.setSku(skuRepository.findById(product.getSku())
                .orElseThrow(() -> new NotExistingSkuException(product.getSku()))
        );

        return productMapper.toProductDTO(productRepository.save(productEntity));
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }
}
