package com.sparta.ordermanagement.framework.persistence.entity.orderproduct;


import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.application.domain.orderproduct.OrderProduct;
import com.sparta.ordermanagement.application.domain.orderproduct.OrderProductForCreate;
import com.sparta.ordermanagement.framework.persistence.entity.BaseEntity;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import com.sparta.ordermanagement.framework.persistence.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "p_order_product")
public class OrderProductEntity extends BaseEntity {

    @Id
    @Column(name = "order_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity productEntity;

    @Column(nullable = false, columnDefinition = "INTEGER")
    private int count;

    @Column(nullable = false, columnDefinition = "INTEGER")
    private int orderPrice;

    private OrderProductEntity(OrderEntity orderEntity, ProductEntity productEntity, int count, int orderPrice) {

        this.orderEntity = orderEntity;
        this.productEntity = productEntity;
        this.count = count;
        this.orderPrice = orderPrice;
    }

    public static OrderProductEntity from(OrderForCreate orderForCreate, OrderEntity orderEntity, ProductEntity productEntity) {

        List<OrderProductForCreate> products = orderForCreate.productList();
        OrderProductForCreate orderProductForCreate = getProductDetailsByProductId(products, productEntity.getProductUuid()).get();

        return new OrderProductEntity(
                orderEntity,
                productEntity,
                orderProductForCreate.count(),
                orderProductForCreate.orderPrice());
    }

    public OrderProduct toDomain() {
        return new OrderProduct(id, orderEntity.toDomain(), productEntity.toDomain(), count, orderPrice);
    }

    private static Optional<OrderProductForCreate> getProductDetailsByProductId(List<OrderProductForCreate> productList, String productUuid) {
        return productList.stream()
                .filter(product -> product.productId().equals(productUuid)).findFirst();
    }
}
