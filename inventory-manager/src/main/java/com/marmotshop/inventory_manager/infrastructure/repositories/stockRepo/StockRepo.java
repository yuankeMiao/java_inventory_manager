package com.marmotshop.inventory_manager.infrastructure.repositories.stockRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.marmotshop.inventory_manager.domain.stockAggregate.IStockRepo;
import com.marmotshop.inventory_manager.domain.stockAggregate.Stock;

@Repository
public class StockRepo implements IStockRepo {

    @Autowired
    private IStockJpaRepo _stockJpaRepo;

    @Override
    public List<Stock> getAllStocks() {
        return _stockJpaRepo.findAll();
    }

    @Override
    public Page<Stock> getAllStocks(Pageable pageable){
        return _stockJpaRepo.findAll(pageable);
    }

    @Override
    public Optional<Stock> getStockById(UUID stockId){
        return _stockJpaRepo.findById(stockId);
    }

    @Override
    public Page<Stock> getStocksByProductId(UUID productId, Pageable pageable){
        return _stockJpaRepo.findByProductId(productId, pageable);
    }

    @Override
    public Page<Stock> getStocksBySupplierId(UUID supplierId, Pageable pageable){
        return _stockJpaRepo.findBySupplierId(supplierId, pageable);
    }

    @Override
    public Page<Stock> getStocksByProductIdAndSupplierId(UUID productId, UUID supplierId, Pageable pageable){
        return _stockJpaRepo.findByProductIdAndSupplierId(productId, supplierId, pageable);
    }


    @Override
    public Stock createStock(Stock newStock){
        return _stockJpaRepo.save(newStock);
    }

    @Override
    public void deleteStock(Stock Stock){
        _stockJpaRepo.delete(Stock);
    }

    @Override
    public Stock updateStock(Stock updatedStock){
        return _stockJpaRepo.save(updatedStock);
    }
}
