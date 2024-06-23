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
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import com.marmotshop.inventory_manager.application.orderService.orderDtos.*;
import com.marmotshop.inventory_manager.application.orderService.orderItemDtos.OrderItemReadDto;
import com.marmotshop.inventory_manager.application.orderService.orderQueryOptions.OrderQueryOptions;
import com.marmotshop.inventory_manager.application.shared.*;
import com.marmotshop.inventory_manager.domain.orderAggregate.*;
import com.marmotshop.inventory_manager.domain.orderItemAggregate.IOrderItemRepo;
import com.marmotshop.inventory_manager.domain.orderItemAggregate.OrderItem;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepo _orderRepo;

    @Autowired
    private IOrderItemRepo _orderItemRepo;

    @Autowired
    OrderMapper _orderMapper;

    @Autowired
    OrderItemMapper _orderItemMapper;

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

        List<OrderReadDto> orderReadDtos = orders.stream().map(order -> {
            OrderReadDto orderReadDto = _orderMapper.entityToReadDto(order);
            List<OrderItem> orderItems = _orderItemRepo.getOrderItemsByOrderId(order.getId());
            List<OrderItemReadDto> orderItemReadDtos = _orderItemMapper.entityToReadDtoList(orderItems);
            orderReadDto.setOrderItemsReadDtos(orderItemReadDtos);
            return orderReadDto;
        }).collect(Collectors.toList());

        return new ResponsePage<>(orders.getTotalElements(), queryOptions.getPage(), queryOptions.getLimit(),
                orderReadDtos);
    }

    @Override
    public OrderReadDto getOrderById(UUID orderId) {
        Order foundOrder = _orderRepo.getOrderById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + orderId));

        OrderReadDto orderReadDto = _orderMapper.entityToReadDto(foundOrder);
        List<OrderItem> orderItems = _orderItemRepo.getOrderItemsByOrderId(orderId);
        List<OrderItemReadDto> orderItemReadDtos = _orderItemMapper.entityToReadDtoList(orderItems);
        orderReadDto.setOrderItemsReadDtos(orderItemReadDtos);

        return orderReadDto;
    }

    @Override
    @Transactional
    public OrderReadDto createOrder(OrderCreateDto orderCreateDto) {
        Set<ConstraintViolation<OrderCreateDto>> violations = _validator.validate(orderCreateDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed: ", violations);
        }

        Order newOrder = _orderMapper.createDtoToEntity(orderCreateDto);
        try {
            // create order 
            Order savedOrder = _orderRepo.createOrder(newOrder);

            // create orderItems
            List<OrderItem> orderItems = orderCreateDto.getOrderItemsCreateDtos().stream()
                    .map(itemDto -> {
                        OrderItem orderItem = _orderItemMapper.createDtoToEntity(itemDto);
                        orderItem.setOrder(savedOrder);
                        return orderItem;
                    }).collect(Collectors.toList());
            _orderItemRepo.createAllOrderItems(orderItems);

            // entity to read dto for order
            OrderReadDto orderReadDto = _orderMapper.entityToReadDto(savedOrder);

            // entity to read dto for orderItems, then set it
            List<OrderItemReadDto> orderItemReadDtos = _orderItemMapper.entityToReadDtoList(orderItems);
            orderReadDto.setOrderItemsReadDtos(orderItemReadDtos);

            return orderReadDto;
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(
                    "Failed to save order with supplier: " + orderCreateDto.getSupplierId(), ex);
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
