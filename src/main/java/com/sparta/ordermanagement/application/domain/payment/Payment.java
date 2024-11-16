package com.sparta.ordermanagement.application.domain.payment;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.framework.persistence.entity.payment.PaymentType;

public class Payment {

    private Long id;
    private String orderUuid;
    private String paymentUuid;
    private int amount;
    private PaymentState paymentState;
    private String pgProvider;

    public Payment(Long id, String orderUuid, String paymentUuid, int amount,
                   PaymentState paymentState, String pgProvider) {

        this.id = id;
        this.orderUuid = orderUuid;
        this.paymentUuid = paymentUuid;
        this.amount = amount;
        this.paymentState = paymentState;
        this.pgProvider = pgProvider;
    }

    public Long getId() {
        return id;
    }

    public String getOrderUuid() {
        return orderUuid;
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

    public String getPgProvider() {
        return pgProvider;
    }
}
