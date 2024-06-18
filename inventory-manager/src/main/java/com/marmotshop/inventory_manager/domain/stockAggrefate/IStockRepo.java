package com.marmotshop.inventory_manager.domain.stockAggrefate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStockRepo {
    public List<Stock> getAllStocks();
    public List<Stock> getAllStocks(StockQueryOptions queryOptions);
    public Optional<Stock> getStockById(UUID StockId);
    public Stock createStock(Stock newStock);
    public void deleteStock(Stock Stock);
    public Stock updateStock(Stock updatedStock);
}
