package ua.okwine.productexpirationdate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.exceptions.NotExistingOrEmptySupplier;
import ua.okwine.productexpirationdate.repository.ProductRepository;
import ua.okwine.productexpirationdate.repository.SupplierRepository;
import ua.okwine.productexpirationdate.rest.dto.ProductDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithReportedDTO;
import ua.okwine.productexpirationdate.rest.dto.ProductWithSupplierIdDTO;
import ua.okwine.productexpirationdate.rest.dto.mapper.ProductMapper;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ProductService {

    private SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> findAllNotReported() {
        return productMapper.toProductDTOList(productRepository.findByIsReportedFalse());
    }

    public List<ProductWithReportedDTO> findAllWithReported() {
        return productMapper.toProductWithReportedDTOList(productRepository.findAll());
    }

    public List<ProductDTO> findAllWithEmptyImage() {
        return productMapper.toProductDTOList(productRepository.findByImageIsNull());
    }

    public ProductDTO findById(UUID id) {
        return productMapper.toProductDTO(productRepository.getById(id));
    }

    public List<ProductDTO> findAllNotReportedByAdvanceNotice(String advanceNotice) {
        var products = productRepository.findAllNotReportedByAdvanceNotice(advanceNotice);

        return productMapper.toProductDTOList(products);
    }

    public ProductDTO save(ProductWithSupplierIdDTO product) {
        var productEntity = productMapper.toProduct(product);
        productEntity.setSupplier(supplierRepository.findById(product.getSupplier())
                .orElseThrow(() -> new NotExistingOrEmptySupplier(product.getSupplier()))
        );

        return productMapper.toProductDTO(productRepository.save(productEntity));
    }

    public List<ProductDTO> saveAll(List<Product> products) {

        return productMapper.toProductDTOList(productRepository.saveAll(products));
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }

    public void deleteAllById(List<UUID> id) {
        productRepository.deleteAllById(id);
    }

    public void updateAll(List<ProductDTO> products) {
        productRepository.saveAll(productMapper.toProductWithIdList(products));
    }
}
