package ua.okwine.productexpirationdate.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @Column(name = "vendor_code")
    private String vendorCode;

    @Column(name = "barcode")
    private String barCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "produced")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate produced;

    @Column(name = "product_exp_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    @ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "image")
    private String image;

    @Column(name = "is_reported")
    private boolean isReported = false;

    public Product(String vendorCode, String barCode, String productName,
                   LocalDate produced, LocalDate expirationDate, Supplier supplier) {
        this.vendorCode = vendorCode;
        this.barCode = barCode;
        this.productName = productName;
        this.produced = produced;
        this.expirationDate = expirationDate;
        this.supplier = supplier;
    }
}
