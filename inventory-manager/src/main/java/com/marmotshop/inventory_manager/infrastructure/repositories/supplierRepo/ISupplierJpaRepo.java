package com.marmotshop.inventory_manager.infrastructure.repositories.supplierRepo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marmotshop.inventory_manager.domain.supplierAggregate.Supplier;

public interface ISupplierJpaRepo extends JpaRepository<Supplier, UUID> {
    public Optional<Supplier> findByName(String name);
}
