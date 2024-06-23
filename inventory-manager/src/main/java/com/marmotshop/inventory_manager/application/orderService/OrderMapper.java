package com.marmotshop.inventory_manager.application.orderService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.marmotshop.inventory_manager.application.orderService.orderDtos.OrderCreateDto;
import com.marmotshop.inventory_manager.application.orderService.orderDtos.OrderReadDto;
import com.marmotshop.inventory_manager.application.orderService.orderDtos.OrderUpdateDto;
import com.marmotshop.inventory_manager.application.supplierService.SupplierMapper;
import com.marmotshop.inventory_manager.domain.orderAggregate.Order;

@Mapper(componentModel = "spring", uses = { OrderItemMapper.class, SupplierMapper.class })
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    // entity to read dto
    @Mapping(source = "supplier", target = "supplierReadDto")
    @Mapping(source = "orderItems", target = "orderItemsReadDtos")
    OrderReadDto entityToReadDto(Order order);

    // create dto to entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "updatedTime", ignore = true)
    @Mapping(source = "supplierId", target = "supplier.id")
    @Mapping(source = "orderItemsCreateDtos", target = "orderItems")
    Order createDtoToEntity(OrderCreateDto orderCreateDto);

    // update dto to entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "updatedTime", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    void updateEntityFromDto(OrderUpdateDto orderUpdateDto, @MappingTarget Order order);
}
