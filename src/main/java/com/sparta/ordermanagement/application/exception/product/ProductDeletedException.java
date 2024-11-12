package com.sparta.ordermanagement.application.exception.product;

import static com.sparta.ordermanagement.application.exception.message.ProductErrorMessage.PRODUCT_DELETED;

import com.sparta.ordermanagement.application.exception.DeletedException;

public class ProductDeletedException extends DeletedException {

    public ProductDeletedException(String productUuid) {
        super(String.format(PRODUCT_DELETED.getMessage(), productUuid));
    }
}
