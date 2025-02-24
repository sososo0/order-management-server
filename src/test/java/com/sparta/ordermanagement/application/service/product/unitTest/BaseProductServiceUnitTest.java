package com.sparta.ordermanagement.application.service.product.unitTest;

import com.sparta.ordermanagement.application.domain.shop.Shop;
import com.sparta.ordermanagement.application.domain.shop.ShopCategory;
import com.sparta.ordermanagement.application.domain.user.User;
import com.sparta.ordermanagement.application.output.product.ProductOutputPort;
import com.sparta.ordermanagement.application.service.product.ProductService;
import com.sparta.ordermanagement.application.service.shop.ShopService;
import com.sparta.ordermanagement.application.service.TestDataForUnitTest;
import com.sparta.ordermanagement.application.service.user.UserService;
import com.sparta.ordermanagement.framework.persistence.entity.region.RegionEntity;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class BaseProductServiceUnitTest {

    protected RegionEntity regionEntity;
    protected ShopCategory shopCategory;
    protected Shop shop;
    protected User owner;

    @BeforeEach
    void setUp() {
        regionEntity = new RegionEntity();
        owner = TestDataForUnitTest.createUser("owner1", Role.OWNER, regionEntity);
        shopCategory = TestDataForUnitTest.createShopCategory("category-uuid", "치킨");
        shop = TestDataForUnitTest.createShop("shop-uuid", shopCategory, "소현이네 치킨집", owner.getUserStringId());
    }

    @InjectMocks
    protected ProductService productService;

    @Mock
    protected ShopService shopService;

    @Mock
    protected UserService userService;

    @Mock
    protected ProductOutputPort productOutputPort;
}
