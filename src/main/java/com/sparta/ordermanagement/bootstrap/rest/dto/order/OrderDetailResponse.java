package com.sparta.ordermanagement.bootstrap.rest.dto.order;

import com.sparta.ordermanagement.application.domain.order.OrderPayment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDetailResponse {

    private final String orderUuid;
    private final String paymentUuid;

    public static OrderDetailResponse from(OrderPayment orderPayment) {
        return new OrderDetailResponse(
                orderPayment.getOrderUuid(),
                orderPayment.getPaymentUuid()
        );
    }
}
