package ua.okwine.productexpirationdate.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

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
