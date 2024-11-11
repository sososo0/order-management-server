package com.sparta.ordermanagement.application.exception.shop;

import static com.sparta.ordermanagement.application.exception.message.ShopErrorMessage.SHOP_UUID_INVALID;

import com.sparta.ordermanagement.application.exception.InvalidValueException;

public class ShopUuidInvalidException extends InvalidValueException {

    public ShopUuidInvalidException(String shopUuid) {
        super(String.format(SHOP_UUID_INVALID.getMessage(), shopUuid));
    }
}
