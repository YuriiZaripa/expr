package ua.okwine.productexpirationdate.exceptions;

public class NotExistingOrEmptySupplierException extends RuntimeException {

    public NotExistingOrEmptySupplierException(int row) {
        super("The supplier in " + row + " row is empty or not yet registered in the app.");
    }
}
