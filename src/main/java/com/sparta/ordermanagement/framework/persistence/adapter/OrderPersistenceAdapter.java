package com.sparta.ordermanagement.framework.persistence.adapter;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.application.output.OrderOutputPort;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import com.sparta.ordermanagement.framework.persistence.entity.orderproduct.OrderProductEntity;
import com.sparta.ordermanagement.framework.persistence.repository.OrderProductRepository;
import com.sparta.ordermanagement.framework.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class OrderPersistenceAdapter implements OrderOutputPort {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    @Transactional
    @Override
    public Order saveOrder(OrderForCreate orderForCreate) {

        OrderEntity orderEntity = orderRepository.save(OrderEntity.from(orderForCreate));

        OrderProductEntity orderProductEntity = OrderProductEntity.from(orderForCreate, orderEntity);
        orderProductRepository.save(orderProductEntity);

        return orderEntity.toDomain();
    }
}
