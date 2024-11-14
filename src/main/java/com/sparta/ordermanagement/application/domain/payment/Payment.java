package com.sparta.ordermanagement.application.domain.payment;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.framework.persistence.entity.payment.PaymentType;

public class Payment {

    private Long id;
    private Order order;
    private String paymentUuid;
    private int amount;
    private PaymentState paymentState;
    private PaymentType paymentType;
    private String pgProvider;

    public Payment(Long id, Order order, String paymentUuid, int amount,
                   PaymentState paymentState, PaymentType paymentType, String pgProvider) {

        this.id = id;
        this.order = order;
        this.paymentUuid = paymentUuid;
        this.amount = amount;
        this.paymentState = paymentState;
        this.paymentType = paymentType;
        this.pgProvider = pgProvider;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public String getPaymentUuid() {
        return paymentUuid;
    }

    public int getAmount() {
        return amount;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public String getPgProvider() {
        return pgProvider;
    }
}
