package com.sparta.ordermanagement.framework.persistence.adapter;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.application.exception.order.ProductIdInvalidException;
import com.sparta.ordermanagement.application.output.OrderOutputPort;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import com.sparta.ordermanagement.framework.persistence.entity.orderproduct.OrderProductEntity;
import com.sparta.ordermanagement.framework.persistence.entity.product.ProductEntity;
import com.sparta.ordermanagement.framework.persistence.repository.OrderProductRepository;
import com.sparta.ordermanagement.framework.persistence.repository.OrderRepository;
import com.sparta.ordermanagement.framework.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class OrderPersistenceAdapter implements OrderOutputPort {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Order saveOrder(OrderForCreate orderForCreate) {

        OrderEntity orderEntity = orderRepository.save(OrderEntity.from(orderForCreate));

        ProductEntity productEntity = productRepository.findByProductUuid(orderForCreate.productId())
                .orElseThrow(() -> new ProductIdInvalidException(orderForCreate.productId()));

        OrderProductEntity orderProductEntity = OrderProductEntity.from(orderForCreate, orderEntity, productEntity);
        orderProductRepository.save(orderProductEntity);

        return orderEntity.toDomain();
    }
}
