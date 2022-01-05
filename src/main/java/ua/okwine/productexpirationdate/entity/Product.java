package ua.okwine.productexpirationdate.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "vendor_code")
    private String vendorCode;

    @Column(name = "barcode")
    private String barCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "produced")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date produced;

    @Column(name = "product_exp_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Product() {
    }

    public Product(String vendorCode, String barCode, String productName,
                   Date produced, Date expirationDate, Supplier supplier) {
        this.vendorCode = vendorCode;
        this.barCode = barCode;
        this.productName = productName;
        this.produced = produced;
        this.expirationDate = expirationDate;
        this.supplier = supplier;
    }

    public Product(int id, String vendorCode, String productName,
                   Date produce, Date expirationDate, Supplier supplier) {
        this.id = id;
        this.vendorCode = vendorCode;
        this.productName = productName;
        this.produced = produce;
        this.expirationDate = expirationDate;
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getProduced() {
        return produced;
    }

    public void setProduced(Date produce) {
        this.produced = produce;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
