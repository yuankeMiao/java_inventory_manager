package com.marmotshop.inventory_manager.application.orderService;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import com.marmotshop.inventory_manager.application.orderService.orderDtos.*;
import com.marmotshop.inventory_manager.application.orderService.orderQueryOptions.OrderQueryOptions;
import com.marmotshop.inventory_manager.application.shared.*;
import com.marmotshop.inventory_manager.domain.orderAggregate.*;

public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepo _orderRepo;

    @Autowired
    OrderMapper _orderMapper;

    @Autowired
    private Validator _validator;

    @Override
    public ResponsePage<OrderReadDto> getAllOrders(OrderQueryOptions queryOptions) {
        Pageable pageable = PageRequest.of(queryOptions.getPage() - 1, queryOptions.getLimit(),
                queryOptions.getOrderBy().equals(OrderByEnum.ASC)
                        ? Sort.by(queryOptions.getSortBy().getFieldName()).ascending()
                        : Sort.by(queryOptions.getSortBy().getFieldName()).descending());

        Page<Order> orders;
        UUID supplierId = queryOptions.getSupplierId();
        OrderStatusEnum status = queryOptions.getStatus();

        if (supplierId != null && status != null) {
            orders = _orderRepo.getOrdersBySupplierIdAndStatus(supplierId, status, pageable);
        } else if (supplierId != null) {
            orders = _orderRepo.getOrdersBySupplierId(supplierId, pageable);
        } else if (status != null) {
            orders = _orderRepo.getOrdersByStatus(status, pageable);
        } else {
            orders = _orderRepo.getAllOrders(pageable);
        }

        List<OrderReadDto> orderReadDtos = orders.stream()
                .map(_orderMapper::entityToReadDto)
                .collect(Collectors.toList());

        return new ResponsePage<>(orders.getTotalElements(), queryOptions.getPage(), queryOptions.getLimit(),
                orderReadDtos);
    }

    @Override
    public OrderReadDto getOrderById(UUID orderId) {
        Order foundOrder = _orderRepo.getOrderById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + orderId));

        OrderReadDto orderReadDto = _orderMapper.entityToReadDto(foundOrder);
        return orderReadDto;
    }

    @Override
    public OrderReadDto createOrder(OrderCreateDto orderCreateDto) {
        Set<ConstraintViolation<OrderCreateDto>> violations = _validator.validate(orderCreateDto);
              if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed: ", violations);
        }

        Order newOrder = _orderMapper.createDtoToEntity(orderCreateDto);
        try{
            Order savedOrder = _orderRepo.createOrder(newOrder);
            return _orderMapper.entityToReadDto(savedOrder);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to save order with supplier: " + orderCreateDto.getSupplierId(), ex);
        }
    }

    @Override
    public OrderReadDto updateOrderById(UUID orderId, OrderUpdateDto orderUpdateDto) {
        Order foundOrder = _orderRepo.getOrderById(orderId)
        .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + orderId));

        _orderMapper.updateEntityFromDto(orderUpdateDto, foundOrder);
        Order updatedOrder = _orderRepo.updateOrder(foundOrder);
        return _orderMapper.entityToReadDto(updatedOrder);

    }

    @Override
    public void deleteOrderById(UUID orderId) {
        Order foundOrder = _orderRepo.getOrderById(orderId)
        .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + orderId));

        _orderRepo.deleteOrder(foundOrder);
    }
}
