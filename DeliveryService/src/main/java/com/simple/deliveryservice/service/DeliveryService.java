package com.simple.deliveryservice.service;

import com.simple.deliveryservice.dg.DeliveryAdapter;
import com.simple.deliveryservice.dto.DeliveryDto;
import com.simple.deliveryservice.dto.UserAddressDto;
import com.simple.deliveryservice.entity.Delivery;
import com.simple.deliveryservice.entity.UserAddress;
import com.simple.deliveryservice.enums.DeliveryStatus;
import com.simple.deliveryservice.exception.ErrorCode;
import com.simple.deliveryservice.exception.SimpleEcommerceException;
import com.simple.deliveryservice.repository.DeliveryRepository;
import com.simple.deliveryservice.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final UserAddressRepository userAddressRepository;
    private final DeliveryRepository deliveryRepository;

    private final DeliveryAdapter deliveryAdapter;

    @Transactional
    public UserAddressDto addUserAddress(Long userId, String address, String alias) {
        UserAddress userAddress = UserAddress.of(userId, address, alias);
        userAddressRepository.save(userAddress);
        return UserAddressDto.toDto(userAddress);
    }

    @Transactional
    public DeliveryDto processDelivery(Long orderId, String productName, Long productCount, String address) {
        Long refCode = deliveryAdapter.processDelivery(productName, productCount, address);
        Delivery delivery = Delivery.of(orderId, productName, productCount, address, refCode, DeliveryStatus.REQUESTED);
        deliveryRepository.save(delivery);
        return DeliveryDto.toDto(delivery);
    }

    @Transactional(readOnly = true)
    public DeliveryDto getDelivery(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.NOT_FOUND_DELIVERY));
        return DeliveryDto.toDto(delivery);
    }

    @Transactional(readOnly = true)
    public UserAddressDto getAddress(Long addressId) {
        UserAddress userAddress = userAddressRepository.findById(addressId)
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.NOT_FOUND_USER_ADDRESS));
        return UserAddressDto.toDto(userAddress);
    }

    @Transactional(readOnly = true)
    public UserAddressDto getUserAddress(Long userId) {
        UserAddress userAddressList = userAddressRepository.findByUserId(userId).stream().findFirst()
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.NOT_FOUND_USER_ADDRESS));
        return UserAddressDto.toDto(userAddressList);
    }
}
