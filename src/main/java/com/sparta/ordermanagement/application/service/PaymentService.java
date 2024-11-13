package com.sparta.ordermanagement.application.service;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.payment.Payment;
import com.sparta.ordermanagement.application.domain.payment.PaymentForCreate;
import com.sparta.ordermanagement.application.output.PaymentOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentOutputPort paymentOutputPort;

    public Payment createPayment(PaymentForCreate paymentForCreate, Order order) {
        return null;
    }

}
