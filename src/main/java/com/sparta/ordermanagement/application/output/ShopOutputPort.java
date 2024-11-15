package com.sparta.ordermanagement.application.output;

import com.sparta.ordermanagement.application.domain.shop.Shop;
import com.sparta.ordermanagement.application.domain.shop.ShopForUpdate;
import java.util.Optional;

public interface ShopOutputPort {

    Optional<Shop> findById(String shopId);

    String updateShop(ShopForUpdate shopForUpdate);

    Optional<Shop> findByShopUuid(String shopUuid);
}
