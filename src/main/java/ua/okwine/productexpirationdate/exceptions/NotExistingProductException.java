package ua.okwine.productexpirationdate.exceptions;

import java.util.UUID;

public class NotExistingProductException extends RuntimeException{

    public NotExistingProductException(UUID id) {
        super("Not existing product by id  " + id + ".");
    }
}
