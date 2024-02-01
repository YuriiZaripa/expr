package ua.okwine.productexpirationdate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.okwine.productexpirationdate.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p " +
            "WHERE p.expirationDate <= current_date " +
            "AND p.isReported = false " +
            "AND p.sku.supplier.returnCondition = :type")
    List<Product> findAllNotReportedByAdvanceNotice(@Param("type") String type);

    List<Product> findByIsReportedFalse();

}
