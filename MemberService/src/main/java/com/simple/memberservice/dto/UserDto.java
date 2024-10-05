package com.simple.memberservice.dto;

import com.simple.memberservice.entity.UserEntity;

public record UserDto(
        Long id,
        String loginId,
        String userName
) {
    public static UserDto from(UserEntity userEntity) {
        return new UserDto(userEntity.getId(), userEntity.getLoginId(), userEntity.getUserName());
    }
}
