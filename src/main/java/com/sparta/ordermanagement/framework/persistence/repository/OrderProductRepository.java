package com.sparta.ordermanagement.framework.persistence.repository;

import com.sparta.ordermanagement.application.domain.orderproduct.OrderProduct;
import com.sparta.ordermanagement.framework.persistence.entity.orderproduct.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, String> {

    List<OrderProductEntity> findByOrderEntity_OrderUuid(String orderId);
}
