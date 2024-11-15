package com.sparta.ordermanagement.bootstrap.admin.dto;

import com.sparta.ordermanagement.application.domain.order.OrderState;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class OrderUpdateResponse {

    private Long orderId;
    private String orderUuid;
    private OrderState orderState;
    private OrderType orderType;
    private String deliverAddress;
    private String requestOrder;
    private String userId;
    private String shopId;
    private List<OrderProductResponse> orderProducts;

    private String paymentUuid;

    private History history;

    public static OrderUpdateResponse from(OrderEntity orderEntity, String paymentUuid) {
        List<OrderProductResponse> orderProductResponses = orderEntity.getOrderProducts().stream()
                .map(OrderProductResponse::from)
                .collect(Collectors.toList());

        return new OrderUpdateResponse(
                orderEntity.getId(),
                orderEntity.getOrderUuid(),
                orderEntity.getOrderState(),
                orderEntity.getOrderType(),
                orderEntity.getDeliveryAddress(),
                orderEntity.getRequestOrder(),
                orderEntity.getUserEntity().getUserStringId(),
                orderEntity.getShopId(),
                orderProductResponses,  // 변환된 orderProduct 목록
                paymentUuid,
                History.from(orderEntity)
        );
    }
}
