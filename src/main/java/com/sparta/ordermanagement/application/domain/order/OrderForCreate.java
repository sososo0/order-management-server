package com.sparta.ordermanagement.application.domain.order;

import com.sparta.ordermanagement.framework.persistence.entity.order.OrderState;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderType;

public record OrderForCreate(OrderState orderState,
                             OrderType orderType,
                             String deliveryAddress,
                             String requestOrder,
                             String shopId,
                             String userId,
                             String createUserId) {

}
