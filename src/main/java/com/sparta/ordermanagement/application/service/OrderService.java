package com.sparta.ordermanagement.application.service;

import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.application.domain.shop.Shop;
import com.sparta.ordermanagement.application.output.OrderOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderOutputPort orderOutPutPort;
    private final ShopService shopService;
    private final ProductService productService;

    /* User, Product 기능 통합 후 각 id 검증 메서드 추가 필요*/
    @Transactional
    public String createOrder(OrderForCreate orderForCreate) {
        /* User ID 기반 검증은 User 기능과 통합 후 사용 - 현재는 생략*/

        productService.validateProductUuidAndGetProduct(orderForCreate.productId());

        // OrderService가 ShopService를 의존성 주입하지 않고도 검증할 수 있도록 리팩토링 필요
        shopService.validateShopIdAndGetShop(orderForCreate.shopId());

        return orderOutPutPort.saveOrder(orderForCreate).getOrderUuid();
    }

}
