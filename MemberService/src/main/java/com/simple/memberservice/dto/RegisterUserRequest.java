package com.simple.memberservice.dto;

public record RegisterUserRequest(
        String loginId,
        String userName
) {
}
