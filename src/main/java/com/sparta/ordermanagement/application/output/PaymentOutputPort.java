package com.sparta.ordermanagement.application.output;

import com.sparta.ordermanagement.application.domain.payment.Payment;
import com.sparta.ordermanagement.application.domain.payment.PaymentForUpdate;

public interface PaymentOutputPort {

    String savePayment(String orderId);

    Payment processPayment(PaymentForUpdate paymentForUpdate, int amount);
}
