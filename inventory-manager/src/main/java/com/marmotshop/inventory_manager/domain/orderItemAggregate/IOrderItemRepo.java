package com.marmotshop.inventory_manager.domain.orderItemAggregate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// TODO: need to check it later, to see if i really need those methods
public interface IOrderItemRepo {
    public List<OrderItem> getOrderItemsByOrderId(UUID orderId);
    // public List<OrderItem> getOrderItemsByProductId(UUID productId); //for later inventory report, not sure yet, might remove
    public Optional<OrderItem> getOrderItemById(UUID OrderItemId);
    public List<OrderItem> createAllOrderItems(List<OrderItem> newOrderItems);
    public void deleteOrderItem(OrderItem OrderItem);
}
