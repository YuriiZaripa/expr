package ua.okwine.productexpirationdate.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.okwine.productexpirationdate.exceptions.NotExistedSupplierId;
import ua.okwine.productexpirationdate.rest.dto.SkuDTO;
import ua.okwine.productexpirationdate.rest.dto.SkuWithSupplierIdDTO;
import ua.okwine.productexpirationdate.service.SkuService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/skus")
@RequiredArgsConstructor
public class SkuController {

    private final SkuService skuService;

    @GetMapping()
    public List<SkuDTO> findAll() {
        return skuService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<SkuDTO> findById(@PathVariable UUID id) {
        return skuService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        skuService.deleteById(id);
    }

    @PostMapping()
    public SkuDTO create(@RequestBody SkuWithSupplierIdDTO skuWithSupplierIdDTO) {
        return skuService.create(skuWithSupplierIdDTO);
    }

    @PutMapping("/{id}")
    public SkuDTO update(@PathVariable UUID id, @RequestBody SkuWithSupplierIdDTO skuWithSupplierIdDTO) {
        return skuService.update(id, skuWithSupplierIdDTO);
    }
}