package com.marmotshop.inventory_manager.application.orderService.orderDtos;

import com.marmotshop.inventory_manager.domain.orderAggregate.OrderStatusEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderUpdateDto {
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
}
