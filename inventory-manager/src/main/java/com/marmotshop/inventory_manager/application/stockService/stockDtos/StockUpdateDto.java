package com.marmotshop.inventory_manager.application.stockService.stockDtos;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockUpdateDto {
    private UUID supplierId;

    private UUID productId;

    @Min(value = 0, message = "Quantity cannot be less than 0")
    private int quantity;
}
