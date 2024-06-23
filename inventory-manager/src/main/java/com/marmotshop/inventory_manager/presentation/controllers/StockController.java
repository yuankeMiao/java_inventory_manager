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
import com.marmotshop.inventory_manager.application.stockService.IStockService;
import com.marmotshop.inventory_manager.application.stockService.stockDtos.*;
import com.marmotshop.inventory_manager.application.stockService.stockQueryOptions.*;
import com.marmotshop.inventory_manager.presentation.shared.SuccessResponseEntity;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("api/v1/stocks")
public class StockController {
    @Autowired
    private IStockService _stockService;

    // TODO: now all the enums are case sensitive, only capital letters, it's better to set them case insesitive
    @GetMapping
    private ResponseEntity<SuccessResponseEntity<StockReadDto>> getAllStocks(
            @RequestParam(required = false) UUID productId,
            @RequestParam(required = false) UUID supplierId,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "UPDATED_TIME") StockSortByEnum sortBy, 
            @RequestParam(defaultValue = "ASC") OrderByEnum orderBy) throws MessagingException {

        StockQueryOptions queryOptions = new StockQueryOptions();
        queryOptions.setProductId(productId);
        queryOptions.setSupplierId(supplierId);
        queryOptions.setPage(page);
        queryOptions.setLimit(limit);
        queryOptions.setSortBy(sortBy);
        queryOptions.setOrderBy(orderBy);

        // System.out.println(queryOptions.getPage());
        ResponsePage<StockReadDto> stocks = _stockService.getAllStocks(queryOptions);

        SuccessResponseEntity<StockReadDto> response = SuccessResponseEntity.<StockReadDto>builder()
                .data(stocks).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{stockId}")
    private ResponseEntity<StockReadDto> getStockById(@PathVariable UUID stockId) {
        StockReadDto foundStock = _stockService.getStockById(stockId);
        return ResponseEntity.ok(foundStock);
    }

    @PostMapping
    private ResponseEntity<Void> createStock(@RequestBody StockCreateDto StockCreateDto,
            UriComponentsBuilder ucb) {
        StockReadDto savedStock = _stockService.createStock(StockCreateDto);
        URI locationOfNewStock = ucb.path("api/v1/stocks/{stockId}").buildAndExpand(savedStock.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewStock).build();
    }

    @PutMapping("/{stockId}")
    private ResponseEntity<StockReadDto> updateStock(@PathVariable UUID stockId,
            @RequestBody StockUpdateDto stockUpdateDto) {
        StockReadDto updatedStock = _stockService.updateStockById(stockId, stockUpdateDto);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/{stockId}")
    private ResponseEntity<Void> deleteStock(@PathVariable UUID stockId) {
        _stockService.deleteStockById(stockId);
        return ResponseEntity.noContent().build();
    }
}
