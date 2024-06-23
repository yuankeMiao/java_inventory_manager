package com.marmotshop.inventory_manager.application.supplierService;

import java.util.UUID;

import com.marmotshop.inventory_manager.application.shared.ResponsePage;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierCreateDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierReadDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierUpdateDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierQueryOption.SupplierQueryOptions;

public interface ISupplierService {
    ResponsePage<SupplierReadDto> getAllSuppliers(SupplierQueryOptions queryOptions);

    SupplierReadDto getSupplierById(UUID supplierId);

    SupplierReadDto createSupplier(SupplierCreateDto supplierCreateDto);

    SupplierReadDto updateSupplierById(UUID supplierId, SupplierUpdateDto supplierUpdateDto);

    void deleteSupplierById(UUID supplierId);
}
