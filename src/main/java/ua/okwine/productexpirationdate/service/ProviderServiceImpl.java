package ua.okwine.productexpirationdate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.okwine.productexpirationdate.dao.ProviderRepository;
import ua.okwine.productexpirationdate.entity.Provider;
import ua.okwine.productexpirationdate.excelImport.ExcelImport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public void save(Provider provider) {
        providerRepository.save(provider);
    }

    @Override
    public void saveFromExcel(String path) {
        List<Provider> providerList = ExcelImport.excelProviderImport(path);
        providerRepository.saveAll(providerList);
    }

    @Override
    public Provider findById(int id) {
        return providerRepository.getById(id);
    }

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAllByOrderByProviderNameAsc();
    }

    @Override
    public Map<String, Provider> findAllByName() {
        List<Provider> providerList = findAll();
        Map<String, Provider> providersMapByName = new HashMap<>();

        for(Provider provider : providerList) {
            providersMapByName.put(provider.getProviderName(), provider);
        }

        return providersMapByName;
    }

    @Override
    public void deleteById(int id) {
        providerRepository.deleteById(id);
    }
}
