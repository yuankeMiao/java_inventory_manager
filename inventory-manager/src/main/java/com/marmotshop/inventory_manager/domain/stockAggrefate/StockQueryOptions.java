package com.marmotshop.inventory_manager.domain.stockAggrefate;

import java.util.UUID;

import com.marmotshop.inventory_manager.domain.shared.BaseQueryOptions;

public class StockQueryOptions extends BaseQueryOptions {
    public UUID supplierId;
    public UUID productId;
    public StockSortByEnum sortBy = StockSortByEnum.PRODUCT_NAME;
}
