package com.jamaldev.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DeliverySlotInfo {

    private Long id;
    private Date start;
    private Date end;
    private UUID orderUuid;
}
