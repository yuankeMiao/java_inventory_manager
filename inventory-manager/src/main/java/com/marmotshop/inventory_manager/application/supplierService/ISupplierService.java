package com.marmotshop.inventory_manager.application.supplierService;

import java.util.List;
import java.util.UUID;

import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierCreateDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierReadDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierUpdateDto;
import com.marmotshop.inventory_manager.domain.supplierAggregate.SupplierQueryOptions;

public interface ISupplierService {
    List<SupplierReadDto> getAllSuppliers();

    List<SupplierReadDto> getAllSuppliers(SupplierQueryOptions queryOptions);

    SupplierReadDto getSupplierById(UUID supplierId);

    SupplierReadDto createSupplier(SupplierCreateDto supplierCreateDto);

    SupplierReadDto updateSupplierById(UUID supplierId, SupplierUpdateDto supplierUpdateDto);

    void deleteSuppierById(UUID supplierId);
}
