package com.simple.deliveryservice.dg;

public interface DeliveryAdapter {
    Long processDelivery(String productName, Long productCount, String address);
}
