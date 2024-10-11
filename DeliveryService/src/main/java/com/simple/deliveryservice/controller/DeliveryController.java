package com.simple.deliveryservice.controller;

import com.simple.deliveryservice.dto.*;
import com.simple.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/delivery/address")
    public Response<UserAddressDto> registerAddress(@RequestBody AddressRequest request){
        return Response.success(deliveryService.addUserAddress(request.userId(), request.address(), request.alias()));
    }

    @PostMapping("/delivery/process-delivery")
    public Response<DeliveryDto> processDelivery(@RequestBody DeliveryRequest request){
        return Response.success(deliveryService.processDelivery(request.orderId(),
                request.productName(),
                request.productCount(),
                request.address()));
    }

    @GetMapping("/delivery/deliveries/{deliveryId}")
    public Response<DeliveryDto> getDelivery(@PathVariable Long deliveryId){
        return Response.success(deliveryService.getDelivery(deliveryId));
    }

    @GetMapping("/delivery/address/{addressId}")
    public Response<UserAddressDto> getAddress(@PathVariable Long addressId){
        return Response.success(deliveryService.getAddress(addressId));
    }

    @GetMapping("/delivery/users/{userId}/first-address")
    public Response<UserAddressDto> getUserAddress(@PathVariable Long userId){
        return Response.success(deliveryService.getUserAddress(userId));
    }
}
