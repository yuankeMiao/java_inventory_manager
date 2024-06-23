package com.marmotshop.inventory_manager.infrastructure.repositories.orderRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.marmotshop.inventory_manager.domain.orderAggregate.IOrderRepo;
import com.marmotshop.inventory_manager.domain.orderAggregate.Order;
import com.marmotshop.inventory_manager.domain.orderAggregate.OrderStatusEnum;

@Repository
public class OrderRepo implements IOrderRepo {
    @Autowired
    private IOrderJpaRepo _orderJpaRepo;

    @Override
    public List<Order> getAllOrders() {
        return _orderJpaRepo.findAll();
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return _orderJpaRepo.findAll(pageable);
    }

    @Override
    public Page<Order> getOrdersBySupplierId(UUID supplierId, Pageable pageable) {
        return _orderJpaRepo.findBySupplierId(supplierId, pageable);
    }

    @Override
    public Page<Order> getOrdersByStatus(OrderStatusEnum status, Pageable pageable) {
        return _orderJpaRepo.findByStatus(status, pageable);
    }

    @Override
    public Page<Order> getOrdersBySupplierIdAndStatus(UUID supplierId, OrderStatusEnum status, Pageable pageable) {
        return _orderJpaRepo.findBySupplierIdAndStatus(supplierId, status, pageable);
    }

    @Override
    public Optional<Order> getOrderById(UUID OrderId) {
        return _orderJpaRepo.findById(OrderId);
    }

    @Override
    public Order createOrder(Order newOrder) {
        return _orderJpaRepo.save(newOrder);
    }

    @Override
    public void deleteOrder(Order Order) {
        _orderJpaRepo.delete(Order);
    }

    @Override
    public Order updateOrder(Order updatedOrder) {
        return _orderJpaRepo.save(updatedOrder);
    }
}
