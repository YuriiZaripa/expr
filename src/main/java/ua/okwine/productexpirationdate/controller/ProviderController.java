package ua.okwine.productexpirationdate.controller;

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

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/providersList")
    public String getAll(Model model) {

        model.addAttribute("providers", providerService.findAll());

        return "providers/providersList";
    }

    @GetMapping("/showFormForAdd")
    public String getFormForAdd(Model model) {
        Provider provider = new Provider();

        model.addAttribute("provider", provider);

        return "providers/newProviderForm";
    }

    @PostMapping("/save")
    public String saveProvider(@ModelAttribute("provider") Provider provider) {
        providerService.save(provider);

        return "redirect:providersList";
    }

    @GetMapping("/providerUploadForm")
    public String getUploadForm() {
        return "providers/providerUploadForm";
    }

    @PostMapping("/importProvidersFromExcel")
    public String importExcel(Model model, @RequestParam("file") MultipartFile file) {
        String uploadDirectoryPath = "src/main/resources/temp_files";
        String fullPath = "src/main/resources/temp_files/" + file.getOriginalFilename();

        Path fileNameAndPath = Paths.get(uploadDirectoryPath, file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("msg", "Файл " + file.getOriginalFilename() +
                    " НЕ УДАЛОСЬ ИМПОРТИРОВАТЬ!");

            return "providers/importResult";
        }
        model.addAttribute("msg", "Файл " + file.getOriginalFilename() +
                " успешно загружен.");


        providerService.saveFromExcel(fullPath);
        return "providers/importResult";
    }

    @GetMapping("/updateProviderForm")
    public String updateProvider(@RequestParam("providerId") int id,
                                 Model model) {
        Provider provider = providerService.findById(id);

        model.addAttribute("provider", provider);

        return "providers/newProviderForm";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam("providerId") int id) {
        providerService.deleteById(id);

        return "redirect:providersList";
    }
}
