package com.simple.paymentservice.dto;

public record PaymentRequest(
        Long userId,
        Long orderId,
        Long amountKRW,
        Long paymentMethodId
) {
}
