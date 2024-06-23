package com.marmotshop.inventory_manager.presentation.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marmotshop.inventory_manager.application.orderService.IOrderService;
import com.marmotshop.inventory_manager.application.shared.*;
import com.marmotshop.inventory_manager.application.orderService.orderDtos.*;
import com.marmotshop.inventory_manager.domain.orderAggregate.OrderStatusEnum;
import com.marmotshop.inventory_manager.infrastructure.services.email.EmailService;
import com.marmotshop.inventory_manager.application.orderService.orderQueryOptions.*;
import com.marmotshop.inventory_manager.presentation.shared.SuccessResponseEntity;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private IOrderService _orderService;

     @Autowired
    private EmailService _emailService;

    @GetMapping
    private ResponseEntity<SuccessResponseEntity<OrderReadDto>> getAllOrders(
            @RequestParam(required = false) UUID supplierId,
            @RequestParam(required = false) OrderStatusEnum status,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "UPDATED_TIME") OrderSortByEnum sortBy,
            @RequestParam(defaultValue = "ASC") OrderByEnum orderBy) throws MessagingException {

        OrderQueryOptions queryOptions = new OrderQueryOptions();
        queryOptions.setSupplierId(supplierId);
        queryOptions.setStatus(status);
        queryOptions.setPage(page);
        queryOptions.setLimit(limit);
        queryOptions.setSortBy(sortBy);
        queryOptions.setOrderBy(orderBy);

        // System.out.println(queryOptions.getPage());
        ResponsePage<OrderReadDto> orders = _orderService.getAllOrders(queryOptions);

        SuccessResponseEntity<OrderReadDto> response = SuccessResponseEntity.<OrderReadDto>builder()
                .data(orders).build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{orderId}")
    private ResponseEntity<OrderReadDto> getOrderById(@PathVariable UUID orderId) {
        OrderReadDto foundOrder = _orderService.getOrderById(orderId);
        return ResponseEntity.ok(foundOrder);
    }

    @PostMapping
    private ResponseEntity<Void> createOrder(@RequestBody OrderCreateDto OrderCreateDto,
            UriComponentsBuilder ucb) {
        OrderReadDto savedOrder = _orderService.createOrder(OrderCreateDto);
        URI locationOfNewOrder = ucb.path("api/v1/orders/{orderId}").buildAndExpand(savedOrder.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewOrder).build();
    }

    @PutMapping("/{orderId}")
    private ResponseEntity<OrderReadDto> updateOrder(@PathVariable UUID orderId,
            @RequestBody OrderUpdateDto orderUpdateDto) throws MessagingException {
        OrderReadDto updatedOrder = _orderService.updateOrderById(orderId, orderUpdateDto);
        String emailBody = String.format("Your order with Id: %s updated to %s", orderId, orderUpdateDto.getStatus().toString());
         _emailService.sendHtmlEmail("yuankemiao.dev@gmail.com","An Order Updated", emailBody);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    private ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        _orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }
}
