package com.simple.deliveryservice.dto;

import com.simple.deliveryservice.entity.Delivery;
import com.simple.deliveryservice.enums.DeliveryStatus;

public record DeliveryDto(
        Long id,
        Long orderId,
        String productName,
        Long productCount,
        String address,
        Long referenceCode,
        DeliveryStatus status)
{
    public static DeliveryDto toDto(Delivery delivery){
        return new DeliveryDto(
                delivery.getId(),
                delivery.getOrderId(),
                delivery.getProductName(),
                delivery.getProductCount(),
                delivery.getAddress(),
                delivery.getReferenceCode(),
                delivery.getStatus()
        );
    }
}
