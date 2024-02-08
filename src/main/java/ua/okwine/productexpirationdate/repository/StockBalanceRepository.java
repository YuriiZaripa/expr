package ua.okwine.productexpirationdate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.okwine.productexpirationdate.entity.OkwineStockBalance;

public interface StockBalanceRepository extends JpaRepository<OkwineStockBalance, Long> {

}
