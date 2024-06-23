package com.marmotshop.inventory_manager.application.supplierService;

import java.util.List;
import java.util.Optional;
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
import jakarta.validation.Valid;
import jakarta.validation.Validator;

import com.marmotshop.inventory_manager.application.shared.*;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.*;
import com.marmotshop.inventory_manager.application.supplierService.supplierQueryOptions.SupplierQueryOptions;
import com.marmotshop.inventory_manager.domain.supplierAggregate.*;


@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private ISupplierRepo _supplierRepo;

    @Autowired
    private SupplierMapper _supplierMapper;

    @Autowired
    private Validator _validator;

    @Override
    public ResponsePage<SupplierReadDto> getAllSuppliers(SupplierQueryOptions queryOptions) {

        Pageable pageable = PageRequest.of(queryOptions.getPage() - 1, queryOptions.getLimit(),
                queryOptions.getOrderBy().equals(OrderByEnum.ASC)
                        ? Sort.by(queryOptions.getSortBy().getFieldName()).ascending()
                        : Sort.by(queryOptions.getSortBy().getFieldName()).descending());

        Page<Supplier> suppliers;
        if (queryOptions.getName() != null) {
            suppliers = _supplierRepo.searchByName(queryOptions.getName(), pageable);
        } else {
            suppliers = _supplierRepo.getAllSuppliers(pageable);
        }
        
        List<SupplierReadDto> suppliersReadDto = suppliers.stream().map(_supplierMapper::entityToReadDto)
                .collect(Collectors.toList());

        ResponsePage<SupplierReadDto> responsePage = new ResponsePage<>(suppliersReadDto.size(), queryOptions.getPage(),
                queryOptions.getLimit(), suppliersReadDto);
        return responsePage;
    }

    @Override
    public SupplierReadDto getSupplierById(UUID supplierId) throws EntityNotFoundException {
        Supplier foundSupplier = _supplierRepo.getSupplierById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + supplierId));

        SupplierReadDto supplierReadDto = _supplierMapper.entityToReadDto(foundSupplier);
        return supplierReadDto;
    }

    @Override
    public SupplierReadDto createSupplier(@Valid SupplierCreateDto supplierCreateDto) {
        // so i can finally utilize the errpr message in dto annotation
        Set<ConstraintViolation<SupplierCreateDto>> violations = _validator.validate(supplierCreateDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed: ", violations);
        }

        // duplicated name check
        Optional<Supplier> existingSupplier = _supplierRepo.getSupplierByName(supplierCreateDto.getName());
        if (existingSupplier.isPresent()) {
            throw new DataIntegrityViolationException("Duplicated Name: " + supplierCreateDto.getName());
        }

        // save it!!
        Supplier newSupplier = _supplierMapper.createDtoToEntity(supplierCreateDto);
        try {
            Supplier savedSupplier = _supplierRepo.createSupplier(newSupplier);
            return _supplierMapper.entityToReadDto(savedSupplier);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(
                    "Failed to save supplier with Name: " + supplierCreateDto.getName(), ex);
        }
    }

    @Override
    public SupplierReadDto updateSupplierById(UUID supplierId, SupplierUpdateDto supplierUpdateDto) {
        Set<ConstraintViolation<SupplierUpdateDto>> violations = _validator.validate(supplierUpdateDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed: ", violations);
        }

        Supplier foundSupplier = _supplierRepo.getSupplierById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + supplierId));

        _supplierMapper.updateEntityFromDto(supplierUpdateDto, foundSupplier);
        Supplier updatedSupplier = _supplierRepo.updateSupplier(foundSupplier);
        return _supplierMapper.entityToReadDto(updatedSupplier);
    }

    @Override
    public void deleteSupplierById(UUID supplierId) {
        Supplier foundSupplier = _supplierRepo.getSupplierById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + supplierId));
        _supplierRepo.deleteSupplier(foundSupplier);
    }
}
