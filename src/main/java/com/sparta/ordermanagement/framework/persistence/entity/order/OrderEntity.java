package com.sparta.ordermanagement.framework.persistence.entity.order;

import com.sparta.ordermanagement.framework.persistence.entity.BaseEntity;
import com.sparta.ordermanagement.framework.persistence.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String type;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    private OrderEntity(
            OrderState orderState, String type, UserEntity userEntity) {

        this.orderState = orderState;
        this.type = type;
        this.userEntity = userEntity;
    }

    @PrePersist
    private void prePersistence() {
        orderUuid = UUID.randomUUID().toString();
    }
}
