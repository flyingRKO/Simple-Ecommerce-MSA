package com.simple.deliveryservice.dto;

public record DeliveryRequest(
        Long orderId,
        String productName,
        Long productCount,
        String address
) {
}
