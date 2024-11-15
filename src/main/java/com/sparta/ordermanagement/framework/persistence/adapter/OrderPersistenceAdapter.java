package com.sparta.ordermanagement.framework.persistence.adapter;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.application.domain.order.OrderForUpdate;
import com.sparta.ordermanagement.application.exception.order.ProductIdInvalidException;
import com.sparta.ordermanagement.application.output.OrderOutputPort;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import com.sparta.ordermanagement.framework.persistence.entity.orderproduct.OrderProductEntity;
import com.sparta.ordermanagement.framework.persistence.entity.product.ProductEntity;
import com.sparta.ordermanagement.framework.persistence.repository.OrderProductRepository;
import com.sparta.ordermanagement.framework.persistence.repository.OrderRepository;
import com.sparta.ordermanagement.framework.persistence.repository.ProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrderPersistenceAdapter implements OrderOutputPort {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public Optional<Order> findByOrderUuid(String orderUuid) {
        return orderRepository.findByOrderUuid(orderUuid)
            .map(OrderEntity::toDomain)
            .or(Optional::empty);
    }

    public Optional<Order> findByOrderUuidAndIsDeletedFalse(String orderUuid) {
        return orderRepository.findByOrderUuidAndIsDeletedFalse(orderUuid)
            .map(OrderEntity::toDomain)
            .or(Optional::empty);
    }

    @Transactional
    @Override
    public String saveOrder(OrderForCreate orderForCreate) {

        OrderEntity orderEntity = orderRepository.save(OrderEntity.from(orderForCreate));
        createOrderWithProducts(orderForCreate, orderEntity);

        return orderEntity.getOrderUuid();
    }

    @Override
    public Optional<Order> findByOrderId(String orderId) {
        return orderRepository.findByOrderUuid(orderId)
                .map(OrderEntity::toDomain)
                .or(Optional::empty);
    }

    @Transactional
    @Override
    public String updateOrderState(OrderForUpdate orderForUpdate) {
        OrderEntity orderEntity = orderRepository.findByOrderUuid(orderForUpdate.orderId()).get();
        orderEntity.updateState(orderForUpdate);

        return orderEntity.getOrderUuid();
    }

    @Transactional
    @Override
    public String cancelOrder(Order order) {
        OrderEntity orderEntity = orderRepository.findByOrderUuid(order.getOrderUuid()).get();
        orderEntity.cancelOrder();

        return orderEntity.getOrderUuid();
    }

    private void createOrderWithProducts(OrderForCreate orderForCreate, OrderEntity orderEntity) {
        List<OrderProductEntity> orderProductEntities = orderForCreate.productList().stream()
                .map(orderProductForCreate -> {
                    ProductEntity productEntity = productRepository.findByProductUuid(orderProductForCreate.productId())
                            .orElseThrow(() -> new ProductIdInvalidException(orderProductForCreate.productId()));

                    return OrderProductEntity.from(orderForCreate, orderEntity, productEntity);
                })
                .collect(Collectors.toList());

        orderProductRepository.saveAll(orderProductEntities);
    }
}
