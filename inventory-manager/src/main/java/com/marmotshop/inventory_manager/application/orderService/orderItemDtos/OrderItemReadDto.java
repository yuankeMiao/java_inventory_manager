package com.marmotshop.inventory_manager.application.orderService.orderItemDtos;

import java.util.UUID;

import com.marmotshop.inventory_manager.application.stockService.stockDtos.StockReadDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemReadDto {
    private UUID id;
    private UUID orderId;
    private StockReadDto stockReadDto; 
    private int quantity;
    private double price;
}
