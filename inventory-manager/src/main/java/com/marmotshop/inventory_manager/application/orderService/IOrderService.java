package com.marmotshop.inventory_manager.application.orderService;

import java.util.UUID;

import com.marmotshop.inventory_manager.application.orderService.orderDtos.*;
import com.marmotshop.inventory_manager.application.orderService.orderQueryOptions.OrderQueryOptions;
import com.marmotshop.inventory_manager.application.shared.ResponsePage;

public interface IOrderService {
    ResponsePage<OrderReadDto> getAllOrders(OrderQueryOptions queryOptions);

    OrderReadDto getOrderById(UUID orderId);

    OrderReadDto createOrder(OrderCreateDto orderCreateDto);

    OrderReadDto updateOrderById(UUID orderId, OrderUpdateDto orderUpdateDto);

    void deleteOrderById(UUID orderId);
}
