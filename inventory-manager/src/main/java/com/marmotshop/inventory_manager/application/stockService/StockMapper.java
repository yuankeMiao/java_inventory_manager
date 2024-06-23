package com.marmotshop.inventory_manager.application.stockService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.marmotshop.inventory_manager.application.stockService.stockDtos.*;
import com.marmotshop.inventory_manager.application.supplierService.SupplierMapper;
import com.marmotshop.inventory_manager.domain.stockAggregate.Stock;

@Mapper(componentModel = "spring", uses = {SupplierMapper.class})
public interface StockMapper {
  StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

  // entity to read dto
  @Mapping(source = "supplier", target = "supplierReadDto")
  StockReadDto entityToReadDto(Stock stock);

  // create dto to entity
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdTime", ignore = true)
  @Mapping(target = "updatedTime", ignore = true)
  @Mapping(source = "supplierId", target = "supplier.id")
  Stock createDtoToEntity(StockCreateDto stockCreateDto);

  // update dto to entity
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdTime", ignore = true)
  @Mapping(target = "updatedTime", ignore = true)
  @Mapping(source = "supplierId", target = "supplier.id")
  void updateEntityFromDto(StockUpdateDto stockUpdateDto, @MappingTarget Stock stock);

}
