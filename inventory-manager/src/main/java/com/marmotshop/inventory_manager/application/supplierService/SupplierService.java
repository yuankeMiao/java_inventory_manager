package com.marmotshop.inventory_manager.application.supplierService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierCreateDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierReadDto;
import com.marmotshop.inventory_manager.application.supplierService.supplierDtos.SupplierUpdateDto;
import com.marmotshop.inventory_manager.domain.common.OrderByEnum;
import com.marmotshop.inventory_manager.domain.supplierAggregate.ISupplierRepo;
import com.marmotshop.inventory_manager.domain.supplierAggregate.Supplier;
import com.marmotshop.inventory_manager.domain.supplierAggregate.SupplierQueryOptions;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private ISupplierRepo _supplierRepo;

    @Autowired
    private SupplierMapper _supplierMapper;

    @Override
    public List<SupplierReadDto> getAllSuppliers(SupplierQueryOptions queryOptions) {
        Pageable pageable = PageRequest.of(queryOptions.getPage() - 1, queryOptions.getLimit(),
                queryOptions.getOrderBy().equals(OrderByEnum.ASC) ? Sort.by(queryOptions.getSortBy().name().toLowerCase()).ascending()
                        : Sort.by(queryOptions.getSortBy().name().toLowerCase()).descending());
        Page<Supplier> suppliers = _supplierRepo.getAllSuppliers(pageable);

      // TODO: apply filters, consider using JpaSpecification (still researching)

        List<SupplierReadDto> suppliersReadDto = suppliers.stream().map(_supplierMapper::entityToReadDto)
                .collect(Collectors.toList());
        return suppliersReadDto;
    }

    @Override
    public SupplierReadDto getSupplierById(UUID supplierId) {
        //TODO: apply exception handling
        Supplier foundSupplier =  _supplierRepo.getSupplierById(supplierId).get();
        SupplierReadDto supplierReadDto = _supplierMapper.entityToReadDto(foundSupplier);
        return supplierReadDto;
    }

    @Override
    public SupplierReadDto createSupplier(SupplierCreateDto supplierCreateDto) {
        // check if supplier name is unique
        if(_supplierRepo.getSupplierByName(supplierCreateDto.getName()).isPresent()) {
            throw new DataIntegrityViolationException("Name: " + supplierCreateDto.getName());
        }

        Supplier newSupplier = _supplierMapper.createDtoToEntity(supplierCreateDto);
        Supplier savedSupplier = _supplierRepo.createSupplier(newSupplier);
        return _supplierMapper.entityToReadDto(savedSupplier);
    }

    @Override
    public SupplierReadDto updateSupplierById(UUID supplierId, SupplierUpdateDto supplierUpdateDto) {
        Supplier foundSupplier = _supplierRepo.getSupplierById(supplierId).orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + supplierId));

        _supplierMapper.updateEntityFromDto(supplierUpdateDto, foundSupplier);
        Supplier updatedSupplier = _supplierRepo.updateSupplier(foundSupplier);
        return _supplierMapper.entityToReadDto(updatedSupplier);
    }

    @Override
    public void deleteSupplierById(UUID supplierId) {
        Supplier foundSupplier = _supplierRepo.getSupplierById(supplierId).orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + supplierId));
        _supplierRepo.deleteSupplier(foundSupplier);
    }
}
