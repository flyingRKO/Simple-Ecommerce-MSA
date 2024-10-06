package com.simple.paymentservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NOT_FOUND_PAYMENT_METHOD(HttpStatus.NOT_FOUND, "결제 수단을 찾을 수 없습니다."),
    NOT_MATCHED_PAYMENT_METHOD(HttpStatus.BAD_REQUEST, "결제 수단이 일치하지 않습니다."),
    NOT_FOUND_PAYMENT(HttpStatus.NOT_FOUND, "결제 정보를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
