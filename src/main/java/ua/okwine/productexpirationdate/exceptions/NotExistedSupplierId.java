package ua.okwine.productexpirationdate.exceptions;

public class NotExistedSupplierId extends RuntimeException {

    public NotExistedSupplierId(String message) {
        super(message);
    }

    public NotExistedSupplierId(String message, Throwable cause) {
        super(message, cause);
    }
}
