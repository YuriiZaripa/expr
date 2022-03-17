package ua.okwine.productexpirationdate.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.okwine.productexpirationdate.entity.Supplier;
import ua.okwine.productexpirationdate.service.SupplierService;

import java.util.UUID;


@Controller
@RequestMapping("/suppliers")
@AllArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping("/suppliersList")
    public String getAll(Model model) {

        model.addAttribute("suppliers", supplierService.findAll());

        return "suppliers/suppliersList";
    }

    @GetMapping("/showFormForAdd")
    public String getFormForAdd(Model model) {
        Supplier supplier = new Supplier();

        model.addAttribute("supplier", supplier);

        return "suppliers/newSupplierForm";
    }

    @PostMapping("/save")
    public String saveProvider(@ModelAttribute("provider") Supplier supplier) {
        supplierService.save(supplier);

        return "redirect:suppliersList";
    }

    @GetMapping("/updateSupplierForm")
    public String updateProvider(@RequestParam("supplierId") UUID id,
                                 Model model) {
        Supplier supplier = supplierService.findById(id);

        model.addAttribute("supplier", supplier);

        return "suppliers/newSupplierForm";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam("supplierId") UUID id) {
        supplierService.deleteById(id);

        return "redirect:suppliersList";
    }
}
