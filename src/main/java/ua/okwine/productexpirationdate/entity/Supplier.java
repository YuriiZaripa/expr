package ua.okwine.productexpirationdate.entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Supplier { //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id") //
    private int supplierId;

    @Column(name = "supplier_name") //
    private String supplierName;

    @Column(name = "return_condition")
    private String returnCondition;

    @Column(name = "advance_notice")
    private int advanceNotice;

    @Column(name = "discount")
    private int discount;

    @OneToMany(mappedBy = "supplier",
                cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(String supplierName, String returnCondition, int advanceNotice,
                    int discount) {
        this.supplierName = supplierName;
        this.returnCondition = returnCondition;
        this.advanceNotice = advanceNotice;
        this.discount = discount;
    }

    public Supplier(int supplierId, String supplierName,
                    String returnCondition, int advanceNotice, int discount, List<Product> products) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.returnCondition = returnCondition;
        this.advanceNotice = advanceNotice;
        this.discount = discount;
        this.products = products;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getReturnCondition() {
        return returnCondition;
    }

    public void setReturnCondition(String returnCondition) {
        this.returnCondition = returnCondition;
    }

    public int getAdvanceNotice() {
        return advanceNotice;
    }

    public void setAdvanceNotice(int advanceNotice) {
        this.advanceNotice = advanceNotice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public List<Product> getProducts() {
        return products;
    }

//    public void addProduct(Product product) {
//        this.products.add(product);
//    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
