package com.marmotshop.inventory_manager.application.orderService.orderDtos;

import java.util.List;
import java.util.UUID;

import com.marmotshop.inventory_manager.application.orderService.orderItemDtos.OrderItemCreateDto;
import com.marmotshop.inventory_manager.domain.orderAggregate.OrderStatusEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateDto {
    @NotNull
    private UUID supplierId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @NotNull
    private List<OrderItemCreateDto> orderItemsCreateDtos;
}
