package com.sparta.ordermanagement.application.service.review.unitTest;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.shop.Shop;
import com.sparta.ordermanagement.application.domain.shop.ShopCategory;
import com.sparta.ordermanagement.application.domain.user.User;
import com.sparta.ordermanagement.application.output.ReviewOutputPort;
import com.sparta.ordermanagement.application.service.OrderService;
import com.sparta.ordermanagement.application.service.ProductService;
import com.sparta.ordermanagement.application.service.ReviewService;
import com.sparta.ordermanagement.application.service.ShopService;
import com.sparta.ordermanagement.application.service.TestDataForUnitTest;
import com.sparta.ordermanagement.application.service.UserService;
import com.sparta.ordermanagement.framework.persistence.entity.region.RegionEntity;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class BaseReviewServiceUnitTest {

    protected RegionEntity regionEntity;
    protected User owner;
    protected User customer;
    protected ShopCategory shopCategory;
    protected Shop shop;
    protected Order order;

    @BeforeEach
    void setUp() {
        regionEntity = new RegionEntity();

        owner = TestDataForUnitTest.createUser("owner", Role.OWNER, regionEntity);
        customer = TestDataForUnitTest.createUser("customer", Role.CUSTOMER, regionEntity);
        shopCategory = TestDataForUnitTest.createShopCategory("category-uuid", "치킨");
        shop = TestDataForUnitTest.createShop("shop-uuid", shopCategory, "소현이네 치킨집", owner.getUserStringId());
        order = TestDataForUnitTest.createOrder("order-uuid", shop, customer);
    }

    @InjectMocks
    protected ReviewService reviewService;

    @Mock
    protected UserService userService;

    @Mock
    protected ProductService productService;

    @Mock
    protected ShopService shopService;

    @Mock
    protected OrderService orderService;

    @Mock
    protected ReviewOutputPort reviewOutputPort;
}
