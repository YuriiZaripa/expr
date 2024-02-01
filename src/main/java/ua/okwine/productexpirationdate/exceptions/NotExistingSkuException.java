package ua.okwine.productexpirationdate.exceptions;

import java.util.UUID;

public class NotExistingSkuException extends RuntimeException {

    public NotExistingSkuException(UUID skuId) {
        super("Not existing supplier by id  " + skuId + ".");
    }
}
