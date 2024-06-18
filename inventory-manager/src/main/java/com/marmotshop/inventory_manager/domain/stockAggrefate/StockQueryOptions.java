package com.marmotshop.inventory_manager.domain.stockAggrefate;

import java.util.UUID;

import com.marmotshop.inventory_manager.domain.common.BaseQueryOptions;

public class StockQueryOptions extends BaseQueryOptions {
    public UUID supplierId;
    public UUID productId;
    public StockSortByEnum sortBy = StockSortByEnum.PRODUCT_NAME;
}
