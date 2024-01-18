package ua.okwine.productexpirationdate.exceptions;

public class NotExistingOrEmptySupplier extends RuntimeException{
    public NotExistingOrEmptySupplier(int row) {
        super("The supplier in " + row + " row is empty or not yet registered in the app.");
    }
}
