package com.marmotshop.inventory_manager.domain.orderAggregate;

import java.util.UUID;

import com.marmotshop.inventory_manager.domain.shared.BaseQueryOptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryOptions extends BaseQueryOptions {
    public UUID supplierId;
    public OrderStatusEnum status;
    public OrderSortByEnum sortBy;
}
