package ua.okwine.productexpirationdate.requestWrappers;

import java.util.ArrayList;
import java.util.List;

public class ProductRequestWrapper {
    private List<ProductRequest> productRequestList1 = new ArrayList<>();
    private List<ProductRequest> productRequestList2 = new ArrayList<>();
    private List<ProductRequest> productRequestList3 = new ArrayList<>();

    public ProductRequestWrapper() {
    }

    public ProductRequestWrapper(List<ProductRequest> productRequestList1, List<ProductRequest> productRequestList2, List<ProductRequest> productRequestList3) {
        this.productRequestList1 = productRequestList1;
        this.productRequestList2 = productRequestList2;
        this.productRequestList3 = productRequestList3;
    }

    public List<ProductRequest> getProductRequestList1() {
        return productRequestList1;
    }

    public void setProductRequestList1(List<ProductRequest> productRequestList1) {
        this.productRequestList1 = productRequestList1;
    }

    public List<ProductRequest> getProductRequestList2() {
        return productRequestList2;
    }

    public void setProductRequestList2(List<ProductRequest> productRequestList2) {
        this.productRequestList2 = productRequestList2;
    }

    public List<ProductRequest> getProductRequestList3() {
        return productRequestList3;
    }

    public void setProductRequestList3(List<ProductRequest> productRequestList3) {
        this.productRequestList3 = productRequestList3;
    }
}
