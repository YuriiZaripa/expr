package ua.okwine.productexpirationdate.requestWrappers;

import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Provider;

import java.util.Date;

public class ProductRequest {

    private Product product;
    private double quantity = 0;

    public ProductRequest() {
        this.product = new Product();
    }

    public ProductRequest(int id, String vendorCode, String productName,
                   Date produce, Date expirationDate, Provider provider, int quantity) {
        this.product = new Product(id, vendorCode, productName, produce, expirationDate, provider);
        this.quantity = quantity;
    }

    public ProductRequest(Product product) {
        this.product = product;
    }

    public int getId() {
        return product.getId();
    }

    public void setId(int id) {
        product.setId(id);
    }

    public String getVendorCode() {
        return product.getVendorCode();
    }

    public void setVendorCode(String vendorCode) {
        product.setVendorCode(vendorCode);
    }

    public String getBarCode() {
        return product.getBarCode();
    }

    public void setBarCode(String barCode) {
        product.setBarCode(barCode);
    }

    public String getProductName() {
        return product.getProductName();
    }

    public void setProductName(String productName) {
        product.setProductName(productName);
    }

    public Date getProduced() {
        return product.getProduced();
    }

    public void setProduced(Date produced) {
        product.setProduced(produced);
    }

    public Date getExpirationDate() {
        return product.getExpirationDate();
    }

    public void setExpirationDate(Date expirationDate) {
        product.setExpirationDate(expirationDate);
    }

    public Provider getProvider() {
        return product.getProvider();
    }

    public void setProvider(Provider provider) {
        product.setProvider(provider);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
