package ua.okwine.productexpirationdate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.okwine.productexpirationdate.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p " +
            "where p.expirationDate <= current_date " +
            "and p.provider in (" +
            "select pr.providerId from Provider pr " +
            "where pr.returnCondition = :type)" +
            "order by p.provider.providerName")
    public List<Product> findProductForDailyReportByType(@Param("type") String type);
}
