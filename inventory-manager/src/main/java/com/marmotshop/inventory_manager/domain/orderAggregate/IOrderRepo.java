package com.marmotshop.inventory_manager.domain.orderAggregate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderRepo {
    public List<Order> getAllOrders();
    public List<Order> getAllOrders(OrderQueryOptions queryOptions);
    public Optional<Order> getOrderById(UUID OrderId);
    public Order createOrder(Order newOrder);
    public void deleteOrder(Order Order);
    public Order updateOrder(Order updatedOrder);
}
