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

import com.marmotshop.inventory_manager.application.shared.OrderByEnum;
import com.marmotshop.inventory_manager.application.shared.ResponsePage;
import com.marmotshop.inventory_manager.application.supplierService.ISupplierService;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierCreateDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierReadDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierUpdateDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierQueryOptions.SupplierQueryOptions;
import com.marmotshop.inventory_manager.application.supplierService.supplierQueryOptions.SupplierSortByEnum;
import com.marmotshop.inventory_manager.infrastructure.services.logger.CsvLogger;
import com.marmotshop.inventory_manager.presentation.shared.SuccessResponseEntity;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {
    @Autowired
    private ISupplierService _supplierService;

    @Autowired
    private CsvLogger _logger;

    @GetMapping
    private ResponseEntity<SuccessResponseEntity<SupplierReadDto>> getAllSppliers(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "NAME") SupplierSortByEnum sortBy,
            @RequestParam(defaultValue = "ASC") OrderByEnum orderBy) throws MessagingException {


        SupplierQueryOptions queryOptions = new SupplierQueryOptions();
        queryOptions.setName(name);
        queryOptions.setPage(page);
        queryOptions.setLimit(limit);
        queryOptions.setSortBy(sortBy);
        queryOptions.setOrderBy(orderBy);

        ResponsePage<SupplierReadDto> suppliers = _supplierService.getAllSuppliers(queryOptions);

        SuccessResponseEntity<SupplierReadDto> response = SuccessResponseEntity.<SupplierReadDto>builder()
                .data(suppliers).build();
        return ResponseEntity.ok(response);
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
                _logger.logSupplierAction("CREATE", savedSupplier.getId(), savedSupplier.getName());
        return ResponseEntity.created(locationOfNewSupplier).build();
    }

    @PutMapping("/{supplierId}")
    private ResponseEntity<SupplierReadDto> updateSupplier(@PathVariable UUID supplierId,
            @RequestBody SupplierUpdateDto supplierUpdateDto) {
        SupplierReadDto updatedSupplier = _supplierService.updateSupplierById(supplierId, supplierUpdateDto);
        _logger.logSupplierAction("UPDATE", supplierId, supplierUpdateDto.getName());
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{supplierId}")
    private ResponseEntity<Void> deleteSupplier(@PathVariable UUID supplierId) {
        _supplierService.deleteSupplierById(supplierId);
        _logger.logSupplierAction("DELETE", supplierId, null);
        return ResponseEntity.noContent().build();
    }
}
