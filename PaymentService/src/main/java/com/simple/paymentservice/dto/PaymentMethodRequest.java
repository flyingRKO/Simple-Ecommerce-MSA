package com.simple.paymentservice.dto;

import com.simple.paymentservice.enums.PaymentMethodType;

public record PaymentMethodRequest(
        Long userId,
        PaymentMethodType paymentMethodType,
        String creditCardNumber
) {
}
