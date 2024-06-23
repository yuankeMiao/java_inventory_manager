package com.marmotshop.inventory_manager.infrastructure.repositories.orderItemRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marmotshop.inventory_manager.domain.orderItemAggregate.IOrderItemRepo;
import com.marmotshop.inventory_manager.domain.orderItemAggregate.OrderItem;

@Repository
public class OrderItemRepo implements IOrderItemRepo {

    @Autowired
    private IOrderItemJpaRepo _orderItemJpaRepo;

    @Override
    public List<OrderItem> getOrderItemsByOrderId(UUID orderId){
        return _orderItemJpaRepo.findByOrderId(orderId);
    }

    @Override
    public Optional<OrderItem> getOrderItemById(UUID OrderItemId){
        return _orderItemJpaRepo.findById(OrderItemId);
    }

    @Override
    public List<OrderItem> createAllOrderItems(List<OrderItem> newOrderItems){
        return _orderItemJpaRepo.saveAll(newOrderItems);
    }

    @Override
    public void deleteOrderItem(OrderItem OrderItem){
        _orderItemJpaRepo.delete(OrderItem);
    }
}
