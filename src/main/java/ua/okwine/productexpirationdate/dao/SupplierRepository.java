package ua.okwine.productexpirationdate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.okwine.productexpirationdate.entity.Supplier;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    public List<Supplier> findAllByOrderBySupplierNameAsc();
}
