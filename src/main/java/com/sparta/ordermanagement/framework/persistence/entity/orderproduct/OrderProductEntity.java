package com.sparta.ordermanagement.framework.persistence.entity.orderproduct;


import com.sparta.ordermanagement.framework.persistence.entity.BaseEntity;
import com.sparta.ordermanagement.framework.persistence.entity.product.ProductEntity;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "p_order_product")
public class OrderProductEntity extends BaseEntity {

    @Id
    @Column(name = "order_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "uuid")
    private String OrderProductUuid;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity productEntity;

    @Column(nullable = false, columnDefinition = "INTEGER")
    private int count;

    private OrderProductEntity(
            OrderEntity orderEntity, ProductEntity productEntity, int count) {
        this.orderEntity = orderEntity;
        this.productEntity = productEntity;
        this.count = count;
    }

    @PrePersist
    private void prePersistence() {
        OrderProductUuid = UUID.randomUUID().toString();
    }
}
