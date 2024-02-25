package ua.okwine.productexpirationdate.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "daily_report")
public class DailyReport {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "created")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created =  LocalDate.now();

    private String comment;
}
