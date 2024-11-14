package com.sparta.ordermanagement.application.service;

import com.sparta.ordermanagement.application.domain.shop.Shop;
import com.sparta.ordermanagement.application.domain.shop.ShopForUpdate;
import com.sparta.ordermanagement.application.domain.user.User;
import com.sparta.ordermanagement.application.exception.shop.ShopIdInvalidException;
import com.sparta.ordermanagement.application.exception.shop.ShopOwnerMismatchException;
import com.sparta.ordermanagement.application.exception.shop.ShopUuidInvalidException;
import com.sparta.ordermanagement.application.output.ShopOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShopService {

    private final ShopOutputPort shopOutputPort;
    private final ShopCategoryService shopCategoryService;
    private final UserService userService;

    public String updateShop(ShopForUpdate shopForUpdate) {

        Shop shop = validateShopIdAndGetShop(shopForUpdate.shopId());
        validateShopOwnedBy(shop, shopForUpdate.updateUserId());

        if (!shop.isSameCategory(shopForUpdate.shopCategoryId())) {
            shopCategoryService.validateCategoryId(shopForUpdate.shopCategoryId());
        }
        return shopOutputPort.updateShop(shopForUpdate);
    }

    public Shop validateShopIdAndGetShop(String shopId) {
        return shopOutputPort.findById(shopId)
            .orElseThrow(() -> new ShopIdInvalidException(shopId));
    }

    private void validateShopOwnedBy(Shop shop, String userStringId) {
        User user = userService.findByUserStringId(userStringId);
        if (!shop.isOwnBy(user)) {
            throw new ShopOwnerMismatchException(user.getUserStringId());
        }
    }

    public void validateShopUuid(String shopUuid) {
        shopOutputPort.findByShopUuid(shopUuid)
            .orElseThrow(() -> new ShopUuidInvalidException(shopUuid));
    }
}
