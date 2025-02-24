package com.jamaldev.ecommerce.repository;

import java.util.Date;
import java.util.UUID;

import com.jamaldev.ecommerce.service.DeliverySlotService;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DeliverySlotEntity {
    @Id
    private Long id;
    private DeliverySlotService.DeliveryOption deliveryOption;
    private Date slotStart;
    private Date slotEnd;
    private UUID orderUuid;
}
