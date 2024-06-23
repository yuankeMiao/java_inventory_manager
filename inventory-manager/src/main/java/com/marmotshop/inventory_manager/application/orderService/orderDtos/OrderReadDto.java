package com.marmotshop.inventory_manager.application.orderService.orderDtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.marmotshop.inventory_manager.application.orderService.orderItemDtos.OrderItemReadDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierReadDto;
import com.marmotshop.inventory_manager.domain.orderAggregate.OrderStatusEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReadDto {
    private UUID id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private SupplierReadDto supplierReadDto;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    
    private List<OrderItemReadDto> orderItemsReadDtos;
}
