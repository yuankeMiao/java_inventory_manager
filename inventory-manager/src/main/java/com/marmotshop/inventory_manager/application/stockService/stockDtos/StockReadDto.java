package com.marmotshop.inventory_manager.application.stockService.stockDtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierReadDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockReadDto {
    private UUID id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private SupplierReadDto supplierReadDto;
    private UUID productId;
    private int quantity;
}
