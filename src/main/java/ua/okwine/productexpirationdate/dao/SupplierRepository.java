package ua.okwine.productexpirationdate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.okwine.productexpirationdate.entity.Supplier;

import java.util.List;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    List<Supplier> findAllByOrderBySupplierNameAsc();

    List<Supplier> findByIsActiveTrueOrderBySupplierName();

    List<Supplier> findByReturnConditionAndIsActiveTrueOrderBySupplierName(String advanceNotice);
}
