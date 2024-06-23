package com.marmotshop.inventory_manager.application.stockService.stockQueryOptions;

import java.util.UUID;

import com.marmotshop.inventory_manager.application.shared.BaseQueryOptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockQueryOptions extends BaseQueryOptions {
    public UUID supplierId;
    public UUID productId;
    public StockSortByEnum sortBy = StockSortByEnum.UPDATED_TIME;
}
