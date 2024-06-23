package com.marmotshop.inventory_manager.application.orderService.orderItemDtos;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemCreateDto {
    @NotNull
    private UUID id;

    @NotNull
    private UUID orderId;

    @NotNull
    private UUID stockId;

    @NotNull
    @Min(value = 0)
    private int quantity;

    @NotNull
    @Min(value = (long) 0.1)
    private double price;
}
