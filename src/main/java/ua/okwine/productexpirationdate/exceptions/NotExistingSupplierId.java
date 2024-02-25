package ua.okwine.productexpirationdate.exceptions;

import java.util.UUID;

public class NotExistingSupplierId extends RuntimeException {

    private static final String ERROR_MESSAGE = "Supplier not found with id: ";

    public NotExistingSupplierId(UUID supplierId) {
        super(ERROR_MESSAGE + supplierId.toString());
    }

    public NotExistingSupplierId(UUID supplierId, Throwable cause) {
        super(ERROR_MESSAGE + supplierId.toString(), cause);
    }
}
