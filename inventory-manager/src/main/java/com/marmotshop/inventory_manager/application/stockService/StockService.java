package com.marmotshop.inventory_manager.application.stockService;

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
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import com.marmotshop.inventory_manager.application.shared.*;
import com.marmotshop.inventory_manager.application.stockService.stockDtos.*;
import com.marmotshop.inventory_manager.application.stockService.stockQueryOptions.StockQueryOptions;
import com.marmotshop.inventory_manager.domain.stockAggregate.*;

@Service
public class StockService implements IStockService {
    @Autowired
    private IStockRepo _stockRepo;

    @Autowired
    private StockMapper _stockMapper;

    @Autowired
    private Validator _validator;

    @Override
    public ResponsePage<StockReadDto> getAllStocks(StockQueryOptions queryOptions) {
        Pageable pageable = PageRequest.of(queryOptions.getPage() - 1, queryOptions.getLimit(),
                queryOptions.getOrderBy().equals(OrderByEnum.ASC)
                        ? Sort.by(queryOptions.getSortBy().getFieldName()).ascending()
                        : Sort.by(queryOptions.getSortBy().getFieldName()).descending());

        Page<Stock> stocks;
        UUID supplierId = queryOptions.getSupplierId();
        UUID productId = queryOptions.getProductId();
        
        if (supplierId != null && productId != null) {
            stocks = _stockRepo.getStocksByProductIdAndSupplierId(productId, supplierId, pageable);
        } else if (supplierId != null) {
            stocks = _stockRepo.getStocksBySupplierId(supplierId, pageable);
        } else if (productId != null) {
            stocks = _stockRepo.getStocksByProductId(productId, pageable);
        } else {
            stocks = _stockRepo.getAllStocks(pageable);
        }

        List<StockReadDto> stocksReadDto = stocks.stream().map(_stockMapper::entityToReadDto)
                .collect(Collectors.toList());

        ResponsePage<StockReadDto> responsePage = new ResponsePage<>(stocksReadDto.size(), queryOptions.getPage(), queryOptions.getLimit(), stocksReadDto);
        return responsePage;
    }

    @Override
     public StockReadDto getStockById(UUID stockId) {
        Stock foundStock = _stockRepo.getStockById(stockId)
                .orElseThrow(() -> new EntityNotFoundException("Stock not found with id " + stockId));

        StockReadDto stockReadDto = _stockMapper.entityToReadDto(foundStock);
        return stockReadDto;
    }

    @Override
    public StockReadDto createStock(StockCreateDto stockCreateDto){
        Set<ConstraintViolation<StockCreateDto>> violations = _validator.validate(stockCreateDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed: ", violations);
        }

        Stock newStock = _stockMapper.createDtoToEntity(stockCreateDto);
        try {
            Stock savedStock = _stockRepo.createStock(newStock);
            return _stockMapper.entityToReadDto(savedStock);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to save stock with product: " + stockCreateDto.getProductId(), ex);
        }
    }

    @Override
    public StockReadDto updateStockById(UUID stockId, StockUpdateDto stockUpdateDto) {
        Set<ConstraintViolation<StockUpdateDto>> violations = _validator.validate(stockUpdateDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed: ", violations);
        }

        Stock foundStock = _stockRepo.getStockById(stockId)
                .orElseThrow(() -> new EntityNotFoundException("Stock not found with id " + stockId));

        _stockMapper.updateEntityFromDto(stockUpdateDto, foundStock);
        Stock updatedStock = _stockRepo.updateStock(foundStock);
        return _stockMapper.entityToReadDto(updatedStock);
    }

    @Override
    public void deleteStockById(UUID stockId) {
        Stock foundStock = _stockRepo.getStockById(stockId)
                .orElseThrow(() -> new EntityNotFoundException("Stock not found with id " + stockId));
        _stockRepo.deleteStock(foundStock);
    }
}
