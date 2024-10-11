package com.simple.deliveryservice.entity;

import com.simple.deliveryservice.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@Entity
@Table(indexes = {@Index(name = "idx_orderId", columnList = "orderId")})
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private String productName;

    private Long productCount;

    private String address;

    private Long referenceCode;

    @Setter
    private DeliveryStatus status;

    private Delivery(Long orderId, String productName, Long productCount, String address, Long referenceCode, DeliveryStatus status) {
        this.orderId = orderId;
        this.productName = productName;
        this.productCount = productCount;
        this.address = address;
        this.referenceCode = referenceCode;
        this.status = status;
    }

    public static Delivery of(Long orderId, String productName, Long productCount, String address, Long referenceCode, DeliveryStatus status) {
        return new Delivery(orderId, productName, productCount, address, referenceCode, status);
    }
}
