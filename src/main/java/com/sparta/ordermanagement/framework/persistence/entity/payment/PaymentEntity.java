package com.sparta.ordermanagement.framework.persistence.entity.payment;

import com.sparta.ordermanagement.application.domain.payment.PaymentState;
import com.sparta.ordermanagement.framework.persistence.entity.BaseEntity;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "p_payment")
public class PaymentEntity extends BaseEntity {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "payment_uuid")
    private String paymentUuid;

    @Column(columnDefinition = "INTEGER")
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private PaymentState paymentState;

    @Column(columnDefinition = "varchar(255)")
    private String pgProvider;

    @JoinColumn(nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

    private PaymentEntity(
            int amount, PaymentState paymentState,
            String pgProvider, OrderEntity orderEntity) {

        this.amount = amount;
        this.paymentState = paymentState;
        this.pgProvider = pgProvider;
        this.orderEntity = orderEntity;
    }

    public static PaymentEntity from(OrderEntity orderEntity) {

        return new PaymentEntity(
                0,
                PaymentState.PENDING,
                null,
                orderEntity);
    }

    @PrePersist
    private void prePersistence() {
        paymentUuid = UUID.randomUUID().toString();
    }
}
