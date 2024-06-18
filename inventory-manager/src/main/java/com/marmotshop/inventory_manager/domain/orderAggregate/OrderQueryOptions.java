package com.marmotshop.inventory_manager.domain.orderAggregate;

import java.util.UUID;

import com.marmotshop.inventory_manager.domain.common.BaseQueryOptions;

public class OrderQueryOptions extends BaseQueryOptions {
    public UUID supplierId;
    public OrderStatusEnum status;
    public OrderSortByEnum sortBy;
}
