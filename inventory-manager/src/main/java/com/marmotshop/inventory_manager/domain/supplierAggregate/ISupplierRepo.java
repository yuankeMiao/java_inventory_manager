package com.marmotshop.inventory_manager.domain.supplierAggregate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISupplierRepo {
    public List<Supplier> getAllSuppliers();
    public List<Supplier> getAllSuppliers(SupplierQuesryOptions quesryOptions);
    public Optional<Supplier> getSupplierById(UUID supplierId);
    public Optional<Supplier> getSupplierByName(String name);
    public Supplier createSupplier(Supplier newSupplier);
    public void deleteSupplier(Supplier supplier);
    public Supplier updateSupplier(Supplier updatedUser);
}
