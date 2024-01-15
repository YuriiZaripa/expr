package ua.okwine.productexpirationdate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.okwine.productexpirationdate.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    
    @Query("SELECT p FROM Product p " +
        "JOIN p.supplier s " +
        "WHERE p.expirationDate <= current_date " +
        "AND s.supplierId IN (SELECT sp.supplierId FROM Supplier sp WHERE sp.returnCondition = :type) " +
        "ORDER BY s.supplierName")
    public List<Product> findAllByAdvanceNotice(@Param("type") String type);
}
