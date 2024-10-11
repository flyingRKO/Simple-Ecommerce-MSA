package com.simple.deliveryservice.dg;

import com.simple.deliveryservice.entity.Delivery;
import com.simple.deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.simple.deliveryservice.enums.DeliveryStatus.*;

@Component
@RequiredArgsConstructor
public class DeliveryStatusUpdater {
    private final DeliveryRepository deliveryRepository;
    @Scheduled(fixedDelay = 15000)
    public void deliveryStatusUpdate() {
        System.out.println("----------delivery status update----------");

        List<Delivery> inDeliveryList = deliveryRepository.findAllByStatus(IN_DELIVERY);
        inDeliveryList.forEach(delivery -> {
            delivery.setStatus(COMPLETED);
            deliveryRepository.save(delivery);
        });

        List<Delivery> requestedList = deliveryRepository.findAllByStatus(REQUESTED);
        requestedList.forEach(delivery -> {
            delivery.setStatus(IN_DELIVERY);
            deliveryRepository.save(delivery);
        });
    }
}
