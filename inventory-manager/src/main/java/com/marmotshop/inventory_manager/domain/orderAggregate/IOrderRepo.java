package com.marmotshop.inventory_manager.domain.orderAggregate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderRepo {
    public List<Order> getAllOrders();
    public Page<Order> getAllOrders(Pageable pageable);
    public Page<Order> getOrdersBySupplierId(UUID supplierId, org.springframework.data.domain.Pageable pageable);
    public Page<Order> getOrdersByStatus(OrderStatusEnum status, Pageable pageable);
    public Page<Order> getOrdersBySupplierIdAndStatus(UUID supplierId, OrderStatusEnum status, Pageable pageable);
    public Optional<Order> getOrderById(UUID OrderId);
    public Order createOrder(Order newOrder);
    public void deleteOrder(Order Order);
    public Order updateOrder(Order updatedOrder);
}
