package com.marmotshop.inventory_manager.domain.stockAggregate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStockRepo {
    public List<Stock> getAllStocks();
    public Page<Stock> getAllStocks(Pageable pageable);
    public Optional<Stock> getStockById(UUID stockId);
    public Page<Stock> getStocksByProductId(UUID productId, Pageable pageable);
    public Page<Stock> getStocksBySupplierId(UUID supplierId, Pageable pageable);
    public Page<Stock> getStocksByProductIdAndSupplierId(UUID productId, UUID supplierId, Pageable pageable);
    public Stock createStock(Stock newStock);
    public void deleteStock(Stock Stock);
    public Stock updateStock(Stock updatedStock);
}
