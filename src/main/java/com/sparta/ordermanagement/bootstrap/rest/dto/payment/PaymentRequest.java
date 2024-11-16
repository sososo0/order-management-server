package com.sparta.ordermanagement.bootstrap.rest.dto.payment;

import com.sparta.ordermanagement.application.domain.payment.PaymentForUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @NotBlank
    String orderUuid;

    @NotBlank
    String paymentUuid;

    String pgProvider;

    public PaymentForUpdate toDomain(String updatedUserId) {
        return new PaymentForUpdate(orderUuid, paymentUuid, pgProvider, updatedUserId);
    }
}
