package com.marmotshop.inventory_manager.application.orderService.orderQueryOptions;

import java.util.UUID;

import com.marmotshop.inventory_manager.application.shared.BaseQueryOptions;
import com.marmotshop.inventory_manager.domain.orderAggregate.OrderStatusEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderQueryOptions extends BaseQueryOptions {
    private UUID supplierId;
    private OrderStatusEnum status;

    @Enumerated(EnumType.STRING)
    private OrderSortByEnum sortBy;
}
