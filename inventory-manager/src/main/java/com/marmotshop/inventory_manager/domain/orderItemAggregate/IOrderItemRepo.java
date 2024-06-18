package com.marmotshop.inventory_manager.domain.orderItemAggregate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// TODO: need to check it later, to see if i really need those methods
public interface IOrderItemRepo {
    public List<OrderItem> getAllOrderItemsbyOrderId(UUID orderId);
    public List<OrderItem> getAllOrderItemsbyProductId(UUID productId); //for later inventory report, not sure yet, might remove
    public Optional<OrderItem> getOrderItemById(UUID OrderItemId);
    public OrderItem createOrderItem(OrderItem newOrderItem);
    public void deleteOrderItem(OrderItem OrderItem);
    public OrderItem updateOrderItem(OrderItem updatedOrderItem); // might remove, because modify an order item can be forbid
}
