package com.sparta.ordermanagement.application.service;

import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.application.domain.order.OrderForUpdate;
import com.sparta.ordermanagement.application.exception.order.OrderUserIdInvalidException;
import com.sparta.ordermanagement.application.output.OrderOutputPort;
import com.sparta.ordermanagement.application.output.UserOutputPort;
import com.sparta.ordermanagement.framework.persistence.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderOutputPort orderOutPutPort;
    private final ShopService shopService;

    // 추후 UserService 부분 추가 시 삭제 예정
    private final UserOutputPort userOutputPort;

    public String createOrder(OrderForCreate orderForCreate) {
        // OrderService가 ShopService를 의존성 주입하지 않고도 검증할 수 있도록 리팩토링 필요
        shopService.validateShopIdAndGetShop(orderForCreate.shopId());
        UserEntity userEntity = validateUserIdAndGetUserEntity(orderForCreate.userId());

        return orderOutPutPort.saveOrder(orderForCreate, userEntity);
    }

    public String updateOrder(OrderForUpdate orderForUpdate) {
        /* 기능 개발 예정*/
        return null;
    }

    private UserEntity validateUserIdAndGetUserEntity(String userId) {
        return userOutputPort.findByUserId(userId)
                .orElseThrow(() -> new OrderUserIdInvalidException(userId));
    }

}
