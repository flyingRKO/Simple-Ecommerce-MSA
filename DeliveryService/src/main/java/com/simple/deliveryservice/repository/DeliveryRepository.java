package com.simple.deliveryservice.repository;

import com.simple.deliveryservice.entity.Delivery;
import com.simple.deliveryservice.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findAllByOrderId(Long orderId);

    List<Delivery> findAllByStatus(DeliveryStatus status);
}
