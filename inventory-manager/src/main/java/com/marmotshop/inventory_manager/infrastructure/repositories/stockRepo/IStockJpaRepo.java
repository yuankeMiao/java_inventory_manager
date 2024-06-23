package com.marmotshop.inventory_manager.infrastructure.repositories.stockRepo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marmotshop.inventory_manager.domain.stockAggregate.Stock;

public interface IStockJpaRepo extends JpaRepository<Stock, UUID> {
    public List<Stock> findByProductId (UUID productId);
    public List<Stock> findBySupplierId (UUID supplierId);
}
