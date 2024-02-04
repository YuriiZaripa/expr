package ua.okwine.productexpirationdate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.okwine.productexpirationdate.entity.Sku;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SkuRepository extends JpaRepository<Sku, UUID> {

    Optional<Sku> findByTitle(String productName);

    List<Sku> findByImageIsNull();
}
