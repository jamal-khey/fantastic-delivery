package com.jamaldev.ecommerce;

import com.jamaldev.ecommerce.repository.DeliverySlotEntity;
import com.jamaldev.ecommerce.repository.DeliverySlotRepository;
import com.jamaldev.ecommerce.service.DeliverySlotService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }


    @Bean
    CommandLineRunner loadData(DeliverySlotRepository deliverySlotRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                deliverySlotRepository.deleteAll();
                deliverySlotRepository.save(new DeliverySlotEntity(1L,
                        DeliverySlotService.DeliveryOption.DELIVERY,
                        new Date(2025, 2, 20, 10, 0, 0),
                        new Date(2025, 2, 20, 10, 30, 0), null));

                deliverySlotRepository.save(new DeliverySlotEntity(2L,
                        DeliverySlotService.DeliveryOption.DELIVERY,
                        new Date(2025, 2, 20, 10, 30, 0),
                        new Date(2025, 2, 20, 11, 0, 0), null));
                deliverySlotRepository.save(new DeliverySlotEntity(3L,
                        DeliverySlotService.DeliveryOption.DELIVERY,
                        new Date(2025, 2, 20, 11, 0, 0),
                        new Date(2025, 2, 20, 11, 30, 0), null));

                deliverySlotRepository.save(new DeliverySlotEntity(4L,
                        DeliverySlotService.DeliveryOption.DRIVE,
                        new Date(2025, 2, 20, 10, 0, 0),
                        new Date(2025, 2, 20, 10, 30, 0), null));

                deliverySlotRepository.save(new DeliverySlotEntity(5L,
                        DeliverySlotService.DeliveryOption.DRIVE,
                        new Date(2025, 2, 20, 10, 30, 0),
                        new Date(2025, 2, 20, 11, 0, 0), null));
                deliverySlotRepository.save(new DeliverySlotEntity(6L,
                        DeliverySlotService.DeliveryOption.DRIVE,
                        new Date(2025, 2, 20, 11, 0, 0),
                        new Date(2025, 2, 20, 11, 30, 0), null));
            }
        };
    }
}
