package com.sparta.ordermanagement.application.exception.product;

import static com.sparta.ordermanagement.application.exception.message.ProductErrorMessage.PRODUCT_UUID_INVALID;

import com.sparta.ordermanagement.application.exception.InvalidValueException;

public class ProductUuidInvalidException extends InvalidValueException {

    public ProductUuidInvalidException(String productUuid) {
        super(String.format(PRODUCT_UUID_INVALID.getMessage(), productUuid));
    }
}
