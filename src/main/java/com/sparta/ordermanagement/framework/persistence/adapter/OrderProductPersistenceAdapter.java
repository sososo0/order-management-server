package com.sparta.ordermanagement.framework.persistence.adapter;

import com.sparta.ordermanagement.application.domain.orderproduct.OrderProduct;
import com.sparta.ordermanagement.application.output.OrderProductOutputPort;
import com.sparta.ordermanagement.framework.persistence.entity.orderproduct.OrderProductEntity;
import com.sparta.ordermanagement.framework.persistence.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrderProductPersistenceAdapter implements OrderProductOutputPort {

    private final OrderProductRepository orderProductRepository;

    @Transactional
    @Override
    public List<OrderProduct> findOrderProductsByOrderId(String orderId) {

        return orderProductRepository.findByOrderEntity_OrderUuid(orderId)
                .stream().map(OrderProductEntity::toDomain)
                .collect(Collectors.toList());
    }
}
