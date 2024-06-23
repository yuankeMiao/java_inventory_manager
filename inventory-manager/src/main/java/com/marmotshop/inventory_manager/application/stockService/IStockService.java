package com.marmotshop.inventory_manager.application.stockService;

import java.util.UUID;

import com.marmotshop.inventory_manager.application.shared.ResponsePage;
import com.marmotshop.inventory_manager.application.stockService.stockDtos.StockCreateDto;
import com.marmotshop.inventory_manager.application.stockService.stockDtos.StockReadDto;
import com.marmotshop.inventory_manager.application.stockService.stockDtos.StockUpdateDto;
import com.marmotshop.inventory_manager.application.stockService.stockQueryOptions.StockQueryOptions;;

public interface IStockService {
    ResponsePage<StockReadDto> getAllStocks(StockQueryOptions queryOptions);

    StockReadDto getStockById(UUID stockId);

    StockReadDto createStock(StockCreateDto stockCreateDto);

    StockReadDto updateStockById(UUID stockId, StockUpdateDto stockUpdateDto);

    void deleteStockById(UUID stockId);
}
