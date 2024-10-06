package com.simple.paymentservice.entity;

import com.simple.paymentservice.enums.PaymentMethodType;
import com.simple.paymentservice.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(indexes = {@Index(name = "idx_userId", columnList = "userId")})
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(unique = true)
    private Long orderId;

    private Long amountKRW;

    private PaymentMethodType paymentMethodType;

    private String paymentData;

    private PaymentStatus paymentStatus;

    @Column(unique = true)
    private Long referenceCode;

    private Payment(Long userId, Long orderId, Long amountKRW, PaymentMethodType paymentMethodType, PaymentStatus paymentStatus, Long referenceCode) {
        this.userId = userId;
        this.orderId = orderId;
        this.amountKRW = amountKRW;
        this.paymentMethodType = paymentMethodType;
        this.paymentStatus = paymentStatus;
        this.referenceCode = referenceCode;
    }

    public static Payment of(Long userId, Long orderId, Long amountKRW, PaymentMethodType paymentMethodType, String paymentData, PaymentStatus paymentStatus, Long referenceCode){
        return new Payment(userId, orderId, amountKRW, paymentMethodType, paymentStatus, referenceCode);
    }
}
