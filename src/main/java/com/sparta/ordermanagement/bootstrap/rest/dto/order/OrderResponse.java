package com.sparta.ordermanagement.bootstrap.rest.dto.order;

import com.sparta.ordermanagement.application.domain.order.OrderState;
import com.sparta.ordermanagement.application.domain.order.TotalOrder;
import com.sparta.ordermanagement.application.domain.payment.PaymentState;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponse {

    private String orderUuid;
    private String userId;
    private String shopId;
    private String shopName;
    private OrderState orderState;
    private OrderType orderType;
    private String deliveryAddress;
    private String requestOrder;
    private List<OrderProductResponse> orderProductList;
    private String paymentUuid;
    private PaymentState paymentState;
    private Integer amount;
    private String pgProvider;
    private boolean isDeleted;

    public static OrderResponse from(TotalOrder totalOrder) {

        List<OrderProductResponse> orderProductResponseList =
                totalOrder.getOrderProducts().stream()
                        .map(OrderProductResponse::from).toList();

        return new OrderResponse(
                totalOrder.getOrderUuid(),
                totalOrder.getUserId(),
                totalOrder.getShopId(),
                totalOrder.getShopName(),
                totalOrder.getOrderState(),
                totalOrder.getOrderType(),
                totalOrder.getDeliveryAddress(),
                totalOrder.getRequestOrder(),
                orderProductResponseList,
                totalOrder.getPaymentUuid(),
                totalOrder.getPaymentState(),
                totalOrder.getAmount(),
                totalOrder.getPgProvider(),
                totalOrder.getIsDeleted()
        );
    }
}
