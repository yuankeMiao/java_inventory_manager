package com.marmotshop.inventory_manager.domain.stockAggregate;

import java.util.UUID;

import com.marmotshop.inventory_manager.domain.shared.BaseEntity;
import com.marmotshop.inventory_manager.domain.supplierAggregate.Supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"supplier_id", "product_id"})
})
public class Stock extends BaseEntity {

    @ManyToOne
    @JoinColumn
    private Supplier supplier;

    @Column(nullable = false)
    private UUID productId;

    @Column(columnDefinition = "Numeric", nullable = false)
    @Min(value = 0, message = "Quantity cannot be less than 0")
    private int quantity;
}
