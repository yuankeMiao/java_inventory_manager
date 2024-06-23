package com.marmotshop.inventory_manager.infrastructure.repositories.orderRepo;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marmotshop.inventory_manager.domain.orderAggregate.Order;
import com.marmotshop.inventory_manager.domain.orderAggregate.OrderStatusEnum;

public interface IOrderJpaRepo extends JpaRepository<Order, UUID> {
    public Page<Order> findBySupplierId(UUID supplierId, Pageable pageable);

    public Page<Order> findByStatus(OrderStatusEnum status, Pageable pageable);

    public Page<Order> findBySupplierIdAndStatus(UUID supplierId, OrderStatusEnum status, Pageable pageable);
}
