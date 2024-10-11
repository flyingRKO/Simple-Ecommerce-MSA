package com.simple.deliveryservice.dto;

import com.simple.deliveryservice.entity.UserAddress;

public record UserAddressDto(
        Long id,
        Long userId,
        String address,
        String alias
)
{
    public static UserAddressDto toDto(UserAddress userAddress){
        return new UserAddressDto(
                userAddress.getId(),
                userAddress.getUserId(),
                userAddress.getAddress(),
                userAddress.getAlias()
        );
    }
}
