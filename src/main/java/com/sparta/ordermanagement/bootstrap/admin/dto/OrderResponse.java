package com.sparta.ordermanagement.bootstrap.admin.dto;

import com.sparta.ordermanagement.application.domain.order.OrderState;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;
    private String orderUuid;
    private OrderState orderState;
    private OrderType orderType;
    private String deliverAddress;
    private String requestOrder;
    private String userId;
    private String shopId;
    private List<OrderProductResponse> orderProducts;

    private History history;

    public static OrderResponse from(OrderEntity orderEntity) {
        List<OrderProductResponse> orderProductResponses = orderEntity.getOrderProducts().stream()
                .map(OrderProductResponse::from)
                .toList();

        return new OrderResponse(
                orderEntity.getId(),
                orderEntity.getOrderUuid(),
                orderEntity.getOrderState(),
                orderEntity.getOrderType(),
                orderEntity.getDeliveryAddress(),
                orderEntity.getRequestOrder(),
                orderEntity.getUserEntity().getUserStringId(),
                orderEntity.getShopId(),
                orderProductResponses,
                History.from(orderEntity)
        );
    }
}
