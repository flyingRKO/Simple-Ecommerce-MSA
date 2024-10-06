package com.simple.paymentservice.dto;

import com.simple.paymentservice.entity.PaymentMethod;
import com.simple.paymentservice.enums.PaymentMethodType;

public record PaymentMethodDto(
        Long id,
        Long userId,
        PaymentMethodType paymentMethodType,
        String creditCardNumber
) {
    public static PaymentMethodDto toDto(PaymentMethod paymentMethod) {
        return new PaymentMethodDto(
                paymentMethod.getId(),
                paymentMethod.getUserId(),
                paymentMethod.getPaymentMethodType(),
                paymentMethod.getCreditCardNumber()
        );
    }
}
