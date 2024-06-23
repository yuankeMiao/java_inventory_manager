package com.marmotshop.inventory_manager.domain.supplierAggregate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISupplierRepo {
    public List<Supplier> getAllSuppliers();
    public Page<Supplier> getAllSuppliers(Pageable pageable);
    public Page<Supplier> searchByName(String name, Pageable pageable);
    public Optional<Supplier> getSupplierById(UUID supplierId);
    public Optional<Supplier> getSupplierByName(String name);
    public Supplier createSupplier(Supplier newSupplier);
    public void deleteSupplier(Supplier supplier);
    public Supplier updateSupplier(Supplier updatedSupplier);
}
