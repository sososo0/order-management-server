package com.sparta.ordermanagement.application.exception.shop;

import static com.sparta.ordermanagement.application.exception.message.ShopErrorMessage.*;

import com.sparta.ordermanagement.application.exception.InvalidValueException;

public class ShopOwnerMismatchException extends InvalidValueException {

    public ShopOwnerMismatchException(String userStringId) {
        super(String.format(SHOP_OWNER_MISMATCH.getMessage(), userStringId));
    }
}
