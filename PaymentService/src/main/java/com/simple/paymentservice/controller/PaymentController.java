package com.simple.paymentservice.controller;

import com.simple.paymentservice.dto.*;
import com.simple.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payment/methods")
    public Response<PaymentMethodDto> registerPaymentMethod(@RequestBody PaymentMethodRequest request) {
        return Response.success(paymentService.registerPaymentMethod(
                request.userId(),
                request.paymentMethodType(),
                request.creditCardNumber()));
    }

    @PostMapping("/payment/process-payment")
    public Response<PaymentDto> processPayment(@RequestBody PaymentRequest request) {
        return Response.success(paymentService.processPayment(
                request.userId(),
                request.orderId(),
                request.amountKRW(),
                request.paymentMethodId()));
    }

    @GetMapping("/payment/users/{userId}/first-method")
    public Response<PaymentMethodDto> getPaymentMethod(@PathVariable Long userId) {
        return Response.success(paymentService.getPaymentMethodByUser(userId));
    }

    @GetMapping("/payment/payments/{paymentId}")
    public Response<PaymentDto> getPayment(@PathVariable Long paymentId) {
        return Response.success(paymentService.getPayment(paymentId));
    }
}
