package com.jamaldev.ecommerce.service;

import com.jamaldev.ecommerce.dto.DeliverySlotInfo;
import com.jamaldev.ecommerce.repository.DeliverySlotEntity;
import com.jamaldev.ecommerce.repository.DeliverySlotRepository;

import jakarta.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class DeliverySlotService {
    private final DeliverySlotRepository deliverySlotRepository;

    public DeliverySlotService(DeliverySlotRepository deliverySlotRepository) {
        this.deliverySlotRepository = deliverySlotRepository;
    }

    public static DeliverySlotInfo toDeliverySlotInfo(DeliverySlotEntity entity) {
        return new DeliverySlotInfo(entity.getId(), entity.getSlotStart(), entity.getSlotEnd(), entity.getOrderUuid());
    }

    @Transactional
    public ResponseEntity<DeliverySlotInfo> bookDeliverySlotAsap(UUID orderId){
        Optional<DeliverySlotEntity> slotToBook = deliverySlotRepository.findFirstByDeliveryOptionAndOrderUuidIsNullOrderBySlotStartAsc(DeliveryOption.DELIVERY);
        if (slotToBook.isPresent()) {
            DeliverySlotEntity deliverySlot = slotToBook.get();
            deliverySlot.setOrderUuid(orderId);
            var bookedSlot = deliverySlotRepository.save(deliverySlot);
            return new ResponseEntity<>(toDeliverySlotInfo(bookedSlot), HttpStatus.ACCEPTED);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<DeliverySlotInfo> bookDeliverySlotToday(UUID orderId) {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        Date todayEnd = calendar.getTime();

        Optional<DeliverySlotEntity> slotToBook = deliverySlotRepository
            .findFirstByDeliveryOptionAndOrderUuidIsNullAndSlotStartBetweenOrderBySlotStartAsc(
                DeliveryOption.DELIVERY, 
                today, 
                todayEnd
            );
        if (slotToBook.isPresent()) {
            DeliverySlotEntity deliverySlot = slotToBook.get();
            deliverySlot.setOrderUuid(orderId);
            return new ResponseEntity<>(toDeliverySlotInfo(deliverySlotRepository.save(deliverySlot)), HttpStatus.ACCEPTED);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public enum DeliveryOption {
        DRIVE,
        DELIVERY,
    }

}
