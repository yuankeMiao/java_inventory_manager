package com.marmotshop.inventory_manager.application.stockService.stockQueryOptions;

import java.util.UUID;

import com.marmotshop.inventory_manager.application.shared.BaseQueryOptions;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockQueryOptions extends BaseQueryOptions {
    private UUID supplierId;
    private UUID productId;

    @Enumerated(EnumType.STRING)
    private StockSortByEnum sortBy = StockSortByEnum.UPDATED_TIME;
}
