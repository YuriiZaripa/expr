package ua.okwine.productexpirationdate.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.okwine.productexpirationdate.rest.dto.SupplierDTO;
import ua.okwine.productexpirationdate.rest.dto.SupplierWithProductsDTO;
import ua.okwine.productexpirationdate.rest.dto.SuppliersByReturnConditionTypeDTO;
import ua.okwine.productexpirationdate.service.SupplierService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping()
    public List<SupplierDTO> findAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<SupplierDTO> findById(@PathVariable UUID id) {
        return supplierService.findById(id);
    }

    @GetMapping("/{id}/details")
    public Optional<SupplierWithProductsDTO> findByIdWithProducts(@PathVariable UUID id) {
        return supplierService.findByIdWithProducts(id);
    }

    public List<SupplierDTO> findAllActive() {
        return supplierService.findAllActive();
    }

    @GetMapping("/forAllTime")
    public List<SupplierDTO> findAllWithNotActive() {
        return supplierService.findAllWithNotActive();
    }

    @GetMapping("/byReturnConditionType")
    public SuppliersByReturnConditionTypeDTO findAllSeparatedByType() {
        return supplierService.findAllSeparatedByType();
    }

    @PostMapping()
    public SupplierDTO save(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.save(supplierDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        supplierService.deleteById(id);
    }
}
