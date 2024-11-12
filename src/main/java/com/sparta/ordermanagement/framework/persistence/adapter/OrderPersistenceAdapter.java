package com.sparta.ordermanagement.framework.persistence.adapter;

import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.application.output.OrderOutputPort;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import com.sparta.ordermanagement.framework.persistence.entity.user.UserEntity;
import com.sparta.ordermanagement.framework.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderPersistenceAdapter implements OrderOutputPort {

    private final OrderRepository orderRepository;

    @Override
    public String saveOrder(OrderForCreate orderForCreate, UserEntity userEntity) {
        return orderRepository.save(OrderEntity.from(orderForCreate, userEntity)).getOrderUuid();
    }
}
