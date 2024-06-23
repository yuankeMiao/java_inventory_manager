package com.marmotshop.inventory_manager.infrastructure.repositories.orderItemRepo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marmotshop.inventory_manager.domain.orderItemAggregate.OrderItem;

public interface IOrderItemJpaRepo extends JpaRepository<OrderItem, UUID> {
  public List<OrderItem> findByOrderId(UUID orderId);
}
