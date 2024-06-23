package com.marmotshop.inventory_manager.infrastructure.repositories.supplierRepo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marmotshop.inventory_manager.domain.supplierAggregate.Supplier;

public interface ISupplierJpaRepo extends JpaRepository<Supplier, UUID> {
    Optional<Supplier> findByName(String name);
    Page<Supplier> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
