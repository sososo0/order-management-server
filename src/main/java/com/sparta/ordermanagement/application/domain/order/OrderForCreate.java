package com.sparta.ordermanagement.application.domain.order;

import com.sparta.ordermanagement.application.domain.orderproduct.OrderProductForCreate;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderType;

import java.util.List;

public record OrderForCreate(String userId,
                             String shopId,
                             OrderState orderState,
                             OrderType orderType,
                             String deliveryAddress,
                             String requestOrder,
                             /* 아래 데이터는 List 형식으로 받을 수 있게 수정 */
                             List<OrderProductForCreate> productList,
                             String createdUserId) {

}
