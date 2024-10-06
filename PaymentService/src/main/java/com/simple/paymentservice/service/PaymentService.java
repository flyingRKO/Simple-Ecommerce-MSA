package com.simple.paymentservice.service;

import com.simple.paymentservice.dto.PaymentDto;
import com.simple.paymentservice.dto.PaymentMethodDto;
import com.simple.paymentservice.entity.Payment;
import com.simple.paymentservice.entity.PaymentMethod;
import com.simple.paymentservice.enums.PaymentMethodType;
import com.simple.paymentservice.enums.PaymentStatus;
import com.simple.paymentservice.exception.ErrorCode;
import com.simple.paymentservice.exception.SimpleEcommerceException;
import com.simple.paymentservice.pg.CreditCardPaymentAdapter;
import com.simple.paymentservice.repository.PaymentMethodRepository;
import com.simple.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentRepository paymentRepository;
    private final CreditCardPaymentAdapter creditCardPaymentAdapter;

    @Transactional
    public PaymentMethodDto registerPaymentMethod(Long userId, PaymentMethodType paymentMethodType, String creditCardNumber) {
        PaymentMethod paymentMethod = PaymentMethod.of(userId, paymentMethodType, creditCardNumber);
        paymentMethodRepository.save(paymentMethod);
        return PaymentMethodDto.toDto(paymentMethod);
    }

    @Transactional
    public PaymentDto processPayment(Long userId, Long orderId, Long amountKRW, Long paymentMethodId){
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.NOT_FOUND_PAYMENT_METHOD));

        if (paymentMethod.getPaymentMethodType() != PaymentMethodType.CREDIT_CARD) {
            throw new SimpleEcommerceException(ErrorCode.NOT_MATCHED_PAYMENT_METHOD);
        }

        Long refCode = creditCardPaymentAdapter.processCreditPayment(amountKRW, paymentMethod.getCreditCardNumber());

        Payment payment = Payment.of(
                userId,
                orderId,
                amountKRW,
                paymentMethod.getPaymentMethodType(),
                paymentMethod.getCreditCardNumber(),
                PaymentStatus.COMPLETED,
                refCode
        );

        paymentRepository.save(payment);

        return PaymentDto.toDto(payment);
    }

    @Transactional(readOnly = true)
    public PaymentMethodDto getPaymentMethodByUser(Long userId) {
        PaymentMethod paymentMethod = paymentMethodRepository.findByUserId(userId).stream().findFirst()
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.NOT_FOUND_PAYMENT_METHOD));
        return PaymentMethodDto.toDto(paymentMethod);
    }

    @Transactional(readOnly = true)
    public PaymentDto getPayment(Long paymentId){
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.NOT_FOUND_PAYMENT));
        return PaymentDto.toDto(payment);
    }

}
