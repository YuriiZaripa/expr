package ua.okwine.productexpirationdate.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.service.SupplierService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/suppliers")
@AllArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping()
    public List<Supplier> findAllActive() {
        return supplierService.findAllActive();
    }

    @GetMapping("/forAllTime")
    public List<Supplier> findAllWithNotActive() {
        return supplierService.findAllWithNotActive();
    }

    @PostMapping()
    public Supplier saveProvider(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        supplierService.deleteById(id);
    }
}
