package com.sparta.ordermanagement.application.exception.order;

import com.sparta.ordermanagement.application.exception.InvalidValueException;

import static com.sparta.ordermanagement.application.exception.message.OrderProductErrorMessage.PRODUCT_ID_INVALID;

public class ProductIdInvalidException extends InvalidValueException {

    public ProductIdInvalidException(String productId) {
        super(String.format(PRODUCT_ID_INVALID.getMessage(), productId));
    }
}
