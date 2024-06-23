package com.marmotshop.inventory_manager.application.orderService;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.marmotshop.inventory_manager.application.orderService.orderItemDtos.*;
import com.marmotshop.inventory_manager.application.stockService.StockMapper;
import com.marmotshop.inventory_manager.domain.orderItemAggregate.OrderItem;

@Mapper(componentModel = "spring", uses = { StockMapper.class })
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    // entity to read dto
    @Mapping(source = "stock", target = "stockReadDto")
    @Mapping(source = "order.id", target = "orderId")
    OrderItemReadDto entityToReadDto(OrderItem orderItem);

    // List mapping
    List<OrderItemReadDto> EntityToReadDtoList(List<OrderItem> orderItems);

    // create dto to entity
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "stockId", target = "stock.id")
    OrderItem createDtoToEntity(OrderItemCreateDto orderItemCreateDto);
    
    // List mapping
    List<OrderItem> createDtoToEntityList(List<OrderItemCreateDto> orderItemCreateDtos);

}
