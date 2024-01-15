package ua.okwine.productexpirationdate.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public List<Supplier> findAll() {
        return supplierService.findAll();
    }

//    @GetMapping("/showFormForAdd")
//    public String getFormForAdd(Model model) {
//        Supplier supplier = new Supplier();
//
//        model.addAttribute("supplier", supplier);
//
//        return "suppliers/newSupplierForm";
//    }
//
    @PostMapping()
    public Supplier saveProvider(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        supplierService.deleteById(id);
    }
}
