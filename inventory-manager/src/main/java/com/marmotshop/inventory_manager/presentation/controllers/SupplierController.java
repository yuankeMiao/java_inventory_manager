package com.marmotshop.inventory_manager.presentation.controllers;

import java.net.URI;
import java.util.List;
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

import com.marmotshop.inventory_manager.application.supplierService.ISupplierService;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierCreateDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierReadDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierUpdateDto;
import com.marmotshop.inventory_manager.domain.common.OrderByEnum;
import com.marmotshop.inventory_manager.domain.supplierAggregate.SupplierQueryOptions;
import com.marmotshop.inventory_manager.domain.supplierAggregate.SupplierSortByEnum;

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {
    @Autowired
    private ISupplierService _supplierService;

    @GetMapping
    private ResponseEntity<List<SupplierReadDto>> getAllSppliers(
            @RequestParam(required = false) UUID productId,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "NAME") SupplierSortByEnum sortBy,
            @RequestParam(defaultValue = "ASC") OrderByEnum orderBy) {
        SupplierQueryOptions queryOptions = new SupplierQueryOptions();
        queryOptions.setProductId(productId);
        queryOptions.setName(name);
        queryOptions.setPage(page);
        queryOptions.setLimit(limit);
        queryOptions.setSortBy(sortBy);
        queryOptions.setOrderBy(orderBy);

        // System.out.println(queryOptions.getPage());
        List<SupplierReadDto> suppliers = _supplierService.getAllSuppliers(queryOptions);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{supplierId}")
    private ResponseEntity<SupplierReadDto> getSupplerById(@PathVariable UUID supplierId) {
        SupplierReadDto foundSupplier = _supplierService.getSupplierById(supplierId);
        return ResponseEntity.ok(foundSupplier);
    }

    @PostMapping
    private ResponseEntity<Void> createSupplier(@RequestBody SupplierCreateDto SupplierCreateDto,
            UriComponentsBuilder ucb) {
        SupplierReadDto savedSupplier = _supplierService.createSupplier(SupplierCreateDto);
        URI locationOfNewSupplier = ucb.path("api/v1/suppliers/{supplierId}").buildAndExpand(savedSupplier.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewSupplier).build();
    }

    @PutMapping("/{supplierId}")
    private ResponseEntity<SupplierReadDto> updateSupplier(@PathVariable UUID supplierId,
            @RequestBody SupplierUpdateDto supplierUpdateDto) {
        SupplierReadDto updatedSupplier = _supplierService.updateSupplierById(supplierId, supplierUpdateDto);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{supplierId}")
    private ResponseEntity<Void> deleteSupplier(@PathVariable UUID supplierId) {
        _supplierService.deleteSupplierById(supplierId);
        return ResponseEntity.noContent().build();
    }
}
