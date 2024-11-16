package com.sparta.ordermanagement.framework.persistence.adapter;

import com.sparta.ordermanagement.application.domain.payment.Payment;
import com.sparta.ordermanagement.application.domain.payment.PaymentForUpdate;
import com.sparta.ordermanagement.application.output.PaymentOutputPort;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import com.sparta.ordermanagement.framework.persistence.entity.payment.PaymentEntity;
import com.sparta.ordermanagement.framework.persistence.repository.OrderRepository;
import com.sparta.ordermanagement.framework.persistence.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class PaymentPersistenceAdapter implements PaymentOutputPort {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public String savePayment(String orderId) {

        OrderEntity orderEntity = orderRepository.findByOrderUuid(orderId).get();
        PaymentEntity paymentEntity = paymentRepository.save(PaymentEntity.from(orderEntity));
        orderEntity.updatePayment(paymentEntity);

        return paymentEntity.getPaymentUuid();
    }

    @Transactional
    @Override
    public Payment processPayment(PaymentForUpdate paymentForUpdate, int amount) {
        PaymentEntity paymentEntity = paymentRepository.findByPaymentUuid(paymentForUpdate.paymentUuid()).get();
        paymentEntity.processPayment(paymentForUpdate, amount);

        return  paymentEntity.toDomain();
    }
}
