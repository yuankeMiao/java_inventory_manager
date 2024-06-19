package com.marmotshop.inventory_manager.application.supplierService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierCreateDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierReadDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierUpdateDto;
import com.marmotshop.inventory_manager.domain.supplierAggregate.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    // entity to read dto
    SupplierReadDto entityToReadDto(Supplier supplier);

    // create dto to entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "updatedTime", ignore = true)
    Supplier createDtoToEntity(SupplierCreateDto supplierCreateDto);


    // update dto to entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "updatedTime", ignore = true)
    void updateEntityFromDto(SupplierUpdateDto supplierUpdateDto, @MappingTarget Supplier supplier);

}
