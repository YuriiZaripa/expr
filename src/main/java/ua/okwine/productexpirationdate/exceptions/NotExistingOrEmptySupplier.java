package ua.okwine.productexpirationdate.exceptions;

import java.util.UUID;

public class NotExistingOrEmptySupplier extends RuntimeException{
    public NotExistingOrEmptySupplier(int row) {
        super("The supplier in " + row + " row is empty or not yet registered in the app.");
    }

    public NotExistingOrEmptySupplier(UUID supplier) {
        super("Not existing supplier by id  " + supplier + ".");
    }
}
