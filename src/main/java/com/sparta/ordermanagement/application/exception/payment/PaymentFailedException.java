package com.sparta.ordermanagement.application.exception.payment;

import static com.sparta.ordermanagement.application.exception.message.PaymentErrorMessage.PAYMENT_PROCESS_FAILED;

public class PaymentFailedException extends RuntimeException {

    public PaymentFailedException(Throwable cause) {
        super(String.format(PAYMENT_PROCESS_FAILED.getMessage()), cause);
    }
}
