package com.sparta.ordermanagement.framework.persistence.entity.order;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.framework.persistence.entity.BaseEntity;
import com.sparta.ordermanagement.framework.persistence.entity.orderproduct.OrderProductEntity;
import com.sparta.ordermanagement.framework.persistence.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "p_order")
public class OrderEntity extends BaseEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "uuid")
    private String orderUuid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private OrderState orderState;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private OrderType orderType;

    @Column(columnDefinition = "varchar(255)")
    private String deliveryAddress;

    @Column(columnDefinition = "varchar(255)")
    private String requestOrder;

    @Column(columnDefinition = "varchar(255)")
    private String shopId;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProductEntity> orderProducts = new ArrayList<>();

    private OrderEntity(OrderState orderState,
                        OrderType orderType, String deliveryAddress,
                        String requestOrder, String shopId, UserEntity userEntity) {

        id = null;
        this.orderState = orderState;
        this.orderType = orderType;
        this.deliveryAddress = deliveryAddress;
        this.requestOrder = requestOrder;
        this.shopId = shopId;
        this.userEntity = userEntity;
    }

    @PrePersist
    private void prePersistence() {
        orderUuid = UUID.randomUUID().toString();
    }

    public Order toDomain() {

        return new Order(id, orderUuid, orderState, orderType,
                deliveryAddress, requestOrder, shopId, userEntity.getUserId());
    }

    public static OrderEntity from(OrderForCreate orderForCreate) {

        UserEntity createdUser = UserEntity.valueOf(orderForCreate.userId());

        return new OrderEntity(
                orderForCreate.orderState(),
                orderForCreate.orderType(),
                orderForCreate.deliveryAddress(),
                orderForCreate.requestOrder(),
                orderForCreate.shopId(),
                createdUser);
    }
}
