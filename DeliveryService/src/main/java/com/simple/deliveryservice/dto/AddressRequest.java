package com.simple.deliveryservice.dto;

public record AddressRequest(
        Long userId,
        String address,
        String alias
) {
}
