package com.sparta.ordermanagement.bootstrap.admin.dto;

import com.sparta.ordermanagement.application.domain.order.OrderState;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateRequest {

    @NotNull
    private OrderState orderState;

    @NotNull
    private OrderType orderType;
}
