package ua.okwine.productexpirationdate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    private Sku sku;

    @Column(name = "produced")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate produced;

    @Column(name = "product_exp_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    @Column(name = "is_reported")
    private boolean isReported = false;

    public Product(Sku sku, LocalDate produced, LocalDate expirationDate) {
        this.sku = sku;
        this.produced = produced;
        this.expirationDate = expirationDate;
    }
}
