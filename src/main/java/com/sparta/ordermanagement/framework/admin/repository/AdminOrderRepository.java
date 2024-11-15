package com.sparta.ordermanagement.framework.admin.repository;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminOrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByOrderUuid(String orderId);
}
