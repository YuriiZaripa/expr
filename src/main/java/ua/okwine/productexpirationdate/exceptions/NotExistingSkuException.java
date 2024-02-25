package ua.okwine.productexpirationdate.exceptions;

import java.util.UUID;

public class NotExistingSkuException extends RuntimeException {

    public NotExistingSkuException(UUID id) {
        super("Not existing sku by id  " + id + ".");
    }
}
