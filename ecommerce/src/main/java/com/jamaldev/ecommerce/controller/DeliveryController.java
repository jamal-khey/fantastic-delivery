package com.jamaldev.ecommerce.controller;

import com.jamaldev.ecommerce.dto.DeliverySlotInfo;
import com.jamaldev.ecommerce.service.DeliverySlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping(path="/api/v1", produces="application/json")
public class DeliveryController {

    private final DeliverySlotService deliverySlotService;
    public DeliveryController(DeliverySlotService deliverySlotService) {
        this.deliverySlotService = deliverySlotService;
    }

    @PostMapping(path="/delivery_asap/{orderId}", consumes="application/json")
    @Operation(summary = "Book a slot for delivery as soon as possible")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "The delivery slot has been booked.")})
    public ResponseEntity<DeliverySlotInfo> bookDeliverySlotAsap(@PathVariable("orderId") UUID orderId) {
        return deliverySlotService.bookDeliverySlotAsap(orderId);
    }

    @PostMapping(path="/delivery_today/{orderId}", consumes="application/json")
    @Operation(summary = "Book a slot for delivery today")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "The delivery slot for today.")})
    public ResponseEntity<DeliverySlotInfo> bookDeliverySlotToday(@PathVariable("orderId") UUID orderId) {
        return deliverySlotService.bookDeliverySlotToday(orderId);
    }

}
