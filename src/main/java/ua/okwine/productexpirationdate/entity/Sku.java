package ua.okwine.productexpirationdate.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "sku")
public class Sku {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "vendor_code")
    private String vendorCode;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "bar_code", joinColumns = @JoinColumn(name = "sku_id"))
    @Column(name = "bar_code")
    private Set<String> barCode;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "image")
    private String image;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    public Sku(String vendorCode, Set<String> barCode, String title, Supplier supplier) {
        this.vendorCode = vendorCode;
        this.barCode = barCode;
        this.title = title;
        this.supplier = supplier;
    }
}
