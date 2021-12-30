package ua.okwine.productexpirationdate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.okwine.productexpirationdate.entity.Provider;
import ua.okwine.productexpirationdate.service.ProviderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/list-providers")
    public String getAll(Model model) {

        model.addAttribute("providers", providerService.findAll());

        return "providers/list-providers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Provider provider = new Provider();

        model.addAttribute("provider", provider);

        return "providers/provider-form";
    }

    @PostMapping("/save")
    public String saveProvider(@ModelAttribute("provider") Provider provider) {
        providerService.save(provider);

        return "redirect:list-providers";
    }

    @GetMapping("/providerUploadForm")
    public String providerUploadForm() {
        return "providers/provider-upload-form";
    }

    @PostMapping("/importProvidersFromExcel")
    public String importExcel(Model model, @RequestParam("file")MultipartFile file) {
        String uploadDirectory = "src/main/resources/temp_files";
        String fullPath = "src/main/resources/temp_files/" + file.getOriginalFilename();

        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("msg", "Файл " + file.getOriginalFilename() +
                    " НЕ УДАЛОСЬ ИМПОРТИРОВАТЬ!");

            return "providers/import-result";
        }
        model.addAttribute("msg", "Файл " + file.getOriginalFilename() +
                " успешно загружен.");


        providerService.saveFromExcel(fullPath);
        return "providers/import-result";
    }

    @GetMapping("/updateProviderForm")
    public String updateProvider(@RequestParam("providerId") int id,
                               Model model) {
        Provider provider = providerService.findById(id);

        model.addAttribute("provider", provider);

        return "providers/provider-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("providerId") int id) {
        providerService.deleteById(id);

        return "redirect:/providers/list-providers";
    }
}
