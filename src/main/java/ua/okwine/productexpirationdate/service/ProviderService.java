package ua.okwine.productexpirationdate.service;

import ua.okwine.productexpirationdate.dao.ProviderRepository;
import ua.okwine.productexpirationdate.entity.Provider;

import java.util.List;
import java.util.Map;

public interface ProviderService {

    void save(Provider provider);

    void saveFromExcel(String path);

    Provider findById(int id);

    List<Provider>  findAll();

    void deleteById(int id);

    Map<String, Provider> findAllByName();
}
