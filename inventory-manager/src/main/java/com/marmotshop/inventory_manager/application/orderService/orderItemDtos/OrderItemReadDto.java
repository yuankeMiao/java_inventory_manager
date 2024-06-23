package com.marmotshop.inventory_manager.application.orderService.orderItemDtos;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemReadDto {
    private UUID id;
    private UUID orderId;
    private UUID productId;
    private int quantity;
    private double price;
}
