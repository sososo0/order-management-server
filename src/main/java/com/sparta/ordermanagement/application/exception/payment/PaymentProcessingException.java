package com.sparta.ordermanagement.application.exception.payment;

import static com.sparta.ordermanagement.application.exception.message.PaymentErrorMessage.PAYMENT_PROCESS_ERROR;

public class PaymentProcessingException extends RuntimeException {

    public PaymentProcessingException() {
        super(String.format(PAYMENT_PROCESS_ERROR.getMessage()));
    }
}
