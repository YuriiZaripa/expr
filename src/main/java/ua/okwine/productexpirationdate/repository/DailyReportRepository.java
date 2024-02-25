package ua.okwine.productexpirationdate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.okwine.productexpirationdate.entity.DailyReport;

import java.util.UUID;

public interface DailyReportRepository extends JpaRepository<DailyReport, UUID> {
}
