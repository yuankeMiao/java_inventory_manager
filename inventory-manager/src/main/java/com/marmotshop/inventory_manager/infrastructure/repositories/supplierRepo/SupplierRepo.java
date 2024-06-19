package com.marmotshop.inventory_manager.infrastructure.repositories.supplierRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.marmotshop.inventory_manager.domain.supplierAggregate.ISupplierRepo;
import com.marmotshop.inventory_manager.domain.supplierAggregate.Supplier;

public class SupplierRepo implements ISupplierRepo {
    @Autowired
    private ISupplierJpaRepo _supplierJpaRepo;

    @Override
    public List<Supplier> getAllSuppliers() {
        return _supplierJpaRepo.findAll();
    }

    @Override
    public Page<Supplier> getAllSuppliers(Pageable pageable) {
        // TODO: apply filter later
        return _supplierJpaRepo.findAll(pageable);
    }

    public Optional<Supplier> getSupplierById(UUID supplierId) {
        return _supplierJpaRepo.findById(supplierId);
    }

    public Optional<Supplier> getSupplierByName(String name) {
        return _supplierJpaRepo.findByName(name);
    }

    public Supplier createSupplier(Supplier newSupplier) {
        return _supplierJpaRepo.save(newSupplier);
    }

    public Supplier updateSupplier(Supplier updatedSupplier) {
        return _supplierJpaRepo.save(updatedSupplier);
    }

    public void deleteSupplier(Supplier supplier) {
        _supplierJpaRepo.delete(supplier);
    }
}
