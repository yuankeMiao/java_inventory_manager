package com.marmotshop.inventory_manager.infrastructure.repositories.stockRepo;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marmotshop.inventory_manager.domain.stockAggregate.Stock;

public interface IStockJpaRepo extends JpaRepository<Stock, UUID> {
    public Page<Stock> findByProductId (UUID productId, Pageable pageable);
    public Page<Stock> findBySupplierId (UUID supplierId, Pageable pageable);
    public Page<Stock> findByProductIdAndSupplierId (UUID productId, UUID supplierId, Pageable pageable);
}
