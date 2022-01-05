package ua.okwine.productexpirationdate.entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private int providerId;

    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "return_condition")
    private String returnCondition;

    @Column(name = "advance_notice")
    private int advanceNotice;

    @Column(name = "discount")
    private int discount;

    @OneToMany(mappedBy = "provider",
                cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Provider() {
    }

    public Provider(String providerName, String returnCondition, int advanceNotice,
                    int discount) {
        this.providerName = providerName;
        this.returnCondition = returnCondition;
        this.advanceNotice = advanceNotice;
        this.discount = discount;
    }

    public Provider(int providerId, String providerName,
                    String returnCondition, int advanceNotice, int discount, List<Product> products) {
        this.providerId = providerId;
        this.providerName = providerName;
        this.returnCondition = returnCondition;
        this.advanceNotice = advanceNotice;
        this.discount = discount;
        this.products = products;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
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

    public void setProduct(Product product) {
        products.add(product);
    }

    public void setProducts(List<Product> products) {

        this.products = products;
    }
}
