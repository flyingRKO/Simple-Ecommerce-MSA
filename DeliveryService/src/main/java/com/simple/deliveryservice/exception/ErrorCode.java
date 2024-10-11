package com.simple.deliveryservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NOT_FOUND_DELIVERY(HttpStatus.NOT_FOUND, "배송 정보를 찾을 수 없습니다."),
    NOT_FOUND_USER_ADDRESS(HttpStatus.NOT_FOUND, "사용자 주소 정보를 찾을 수 없습니다."),;

    private final HttpStatus status;
    private final String message;
}
