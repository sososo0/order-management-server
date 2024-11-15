package com.sparta.ordermanagement.application.domain.order;

public class OrderPayment {

    private String orderUuid;
    private String paymentUuid;

    public OrderPayment(String orderUuid, String paymentUuid) {
        this.orderUuid = orderUuid;
        this.paymentUuid = paymentUuid;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public String getPaymentUuid() {
        return paymentUuid;
    }
}
