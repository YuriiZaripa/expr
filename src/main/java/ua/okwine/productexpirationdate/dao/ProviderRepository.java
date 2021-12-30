package ua.okwine.productexpirationdate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.okwine.productexpirationdate.entity.Provider;

import java.util.List;
import java.util.Map;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {

    public List<Provider> findAllByOrderByProviderNameAsc();
}
