package ua.okwine.productexpirationdate.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.okwine.productexpirationdate.dto.SupplierDTO;
import ua.okwine.productexpirationdate.service.SupplierService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/suppliers")
@AllArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping()
    public List<SupplierDTO> findAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public SupplierDTO findById(@PathVariable UUID id) {

        return supplierService.findById(id);
    }

    public List<SupplierDTO> findAllActive() {
        return supplierService.findAllActive();
    }

    @GetMapping("/forAllTime")
    public List<SupplierDTO> findAllWithNotActive() {
        return supplierService.findAllWithNotActive();
    }

    @PostMapping()
    public SupplierDTO saveSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.save(supplierDTO);
    }

    @PostMapping("/all")
    public List<SupplierDTO> saveAllSupplier(@RequestBody List<SupplierDTO> supplierDTOList) {
        return supplierService.saveAll(supplierDTOList);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        supplierService.deleteById(id);
    }
}
