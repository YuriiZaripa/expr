package ua.okwine.productexpirationdate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.okwine.productexpirationdate.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("select p from Product p " +
            "where p.expirationDate <= current_date " +
            "and p.supplier in (" +
            "select sp.supplierId from Supplier sp " +
            "where sp.returnCondition = :type)" +
            "order by p.supplier.supplierName")
    public List<Product> findProductForDailyReportByType(@Param("type") String type);
}
