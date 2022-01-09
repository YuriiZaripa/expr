package ua.okwine.productexpirationdate.requestWrappers;

import ua.okwine.productexpirationdate.entity.Product;
import ua.okwine.productexpirationdate.entity.Supplier;

import java.time.LocalDate;
import java.util.UUID;

public class ProductRequest {

    private Product product;
    private double quantity = 0;

    public ProductRequest() {
        this.product = new Product();
    }

    public ProductRequest(UUID id, String vendorCode, String barCode, String productName,
                          LocalDate produce, LocalDate expirationDate, Supplier supplier, int quantity) {
        this.product = new Product(id, vendorCode, barCode, productName, produce, expirationDate, supplier);
        this.quantity = quantity;
    }

    public ProductRequest(Product product) {
        this.product = product;
    }

    public UUID getId() {
        return product.getId();
    }

    public void setId(UUID id) {
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

    public LocalDate getProduced() {
        return product.getProduced();
    }

    public void setProduced(LocalDate produced) {
        product.setProduced(produced);
    }

    public LocalDate getExpirationDate() {
        return product.getExpirationDate();
    }

    public void setExpirationDate(LocalDate expirationDate) {
        product.setExpirationDate(expirationDate);
    }

    public Supplier getSupplier() {
        return product.getSupplier();
    }

    public void setSupplier(Supplier supplier) {
        product.setSupplier(supplier);
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
