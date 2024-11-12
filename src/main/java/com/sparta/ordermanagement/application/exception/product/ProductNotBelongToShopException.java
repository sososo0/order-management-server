package com.sparta.ordermanagement.application.exception.product;

import static com.sparta.ordermanagement.application.exception.message.ProductErrorMessage.PRODUCT_NOT_BELONG_TO_SHOP;

import com.sparta.ordermanagement.application.exception.ForbiddenException;

public class ProductNotBelongToShopException extends ForbiddenException {

    public ProductNotBelongToShopException(String productUuid, String shopUuid) {
        super(String.format(PRODUCT_NOT_BELONG_TO_SHOP.getMessage(), productUuid, shopUuid));
    }
}
