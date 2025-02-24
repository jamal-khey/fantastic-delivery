package com.jamaldev.ecommerce.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jamaldev.ecommerce.service.DeliverySlotService.DeliveryOption;


public interface DeliverySlotRepository extends CrudRepository<DeliverySlotEntity, Long> {

    List<DeliverySlotEntity> findByDeliveryOption(DeliveryOption deliveryOption);
    List<DeliverySlotEntity> findByDeliveryOptionAndOrderUuidIsNull(DeliveryOption deliveryOption);
    
    // Find first available slot by start date for a specific delivery option
    Optional<DeliverySlotEntity> findFirstByDeliveryOptionAndOrderUuidIsNullOrderBySlotStartAsc(DeliveryOption deliveryOption);
    Optional<DeliverySlotEntity> findFirstByDeliveryOptionAndOrderUuidIsNullAndSlotStartBetweenOrderBySlotStartAsc(
        DeliveryOption deliveryOption, 
        Date startDate, 
        Date endDate
    );
}
