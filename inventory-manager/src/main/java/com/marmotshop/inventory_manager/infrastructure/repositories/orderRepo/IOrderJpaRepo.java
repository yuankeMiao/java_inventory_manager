package com.marmotshop.inventory_manager.infrastructure.repositories.orderRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marmotshop.inventory_manager.domain.orderAggregate.Order;
import com.marmotshop.inventory_manager.domain.orderAggregate.OrderStatusEnum;

public interface IOrderJpaRepo extends JpaRepository<Order, UUID> {
    public Page<Order> findBySupplierId(UUID supplierId, Pageable pageable);

    public Page<Order> findByStatus(OrderStatusEnum status, Pageable pageable);

    public Page<Order> findBySupplierIdAndStatus(UUID supplierId, OrderStatusEnum status, Pageable pageable);

    public List<Order> findAllByCreatedTimeBetween(LocalDateTime startDate, LocalDateTime endOfDay);

    public List<Order> findAllByUpdatedTimeBetween(LocalDateTime startDate, LocalDateTime endOfDay);

    @Query(value = "SELECT oi.product_id, SUM(oi.quantity) AS total_quantity " +
            "FROM order_item oi " +
            "JOIN \"order\" o ON oi.order_id = o.id " +
            "WHERE o.created_time BETWEEN :startDate AND :endDate " +
            "GROUP BY oi.product_id " +
            "ORDER BY total_quantity DESC " +
            "LIMIT :limitAmount", nativeQuery = true)
    List<Object[]> findTopTrendingProducts(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate, @Param("limitAmount") int limitAmount);

}
