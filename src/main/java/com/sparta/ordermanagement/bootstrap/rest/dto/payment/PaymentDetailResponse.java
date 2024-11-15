package com.sparta.ordermanagement.bootstrap.rest.dto.payment;

import com.sparta.ordermanagement.application.domain.payment.Payment;
import com.sparta.ordermanagement.application.domain.payment.PaymentState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentDetailResponse {

    private final String paymentUuid;
    private final PaymentState paymentState;
    private final int amount;
    private final String pgProvider;

    public static PaymentDetailResponse fromPayment(Payment payment) {
        return new PaymentDetailResponse(payment.getPaymentUuid(), payment.getPaymentState(),
                payment.getAmount(), payment.getPgProvider());
    }
}
