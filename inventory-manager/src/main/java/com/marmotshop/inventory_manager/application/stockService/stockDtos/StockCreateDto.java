package com.marmotshop.inventory_manager.application.stockService.stockDtos;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCreateDto {
    @NotNull(message = "Supplier Id cannot be null")
    private UUID supplierId;

    @NotNull(message = "Product Id cannot be null")
    private UUID productId;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity cannot be less than 0")
    private int quantity;
}
