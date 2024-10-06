package com.simple.paymentservice.dto;

import com.simple.paymentservice.entity.Payment;
import com.simple.paymentservice.enums.PaymentMethodType;
import com.simple.paymentservice.enums.PaymentStatus;

public record PaymentDto(
        Long id,
        Long userId,
        Long orderId,
        Long amountKRW,
        PaymentMethodType paymentMethodType,
        String paymentData,
        PaymentStatus paymentStatus,
        Long referenceCode
        )
{
    public static PaymentDto toDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getUserId(),
                payment.getOrderId(),
                payment.getAmountKRW(),
                payment.getPaymentMethodType(),
                payment.getPaymentData(),
                payment.getPaymentStatus(),
                payment.getReferenceCode()
        );
    }
}
