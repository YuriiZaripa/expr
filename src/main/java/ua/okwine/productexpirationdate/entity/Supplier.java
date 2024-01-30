package ua.okwine.productexpirationdate.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue
    @Column(name = "supplier_id")
    private UUID supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "return_condition")
    private String returnCondition;

    @Column(name = "advance_notice")
    private int advanceNotice;

    @Column(name = "discount")
    private int discount;

    @Column(name = "is_active")
    private boolean isActive = true;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Supplier(String supplierName, String returnCondition, int advanceNotice,
                    int discount) {
        this.supplierName = supplierName;
        this.returnCondition = returnCondition;
        this.advanceNotice = advanceNotice;
        this.discount = discount;
    }
}
