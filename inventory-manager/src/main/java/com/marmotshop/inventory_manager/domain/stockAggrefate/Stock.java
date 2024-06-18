package com.marmotshop.inventory_manager.domain.stockAggrefate;

import java.util.UUID;

import com.marmotshop.inventory_manager.domain.common.BaseEntity;
import com.marmotshop.inventory_manager.domain.supplierAggregate.Supplier;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stocks")
public class Stock extends BaseEntity {

    @ManyToOne
    @JoinColumn
    private Supplier supplier;

    @Column(nullable = false)
    private UUID product_id;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String barcode;

    @Column(columnDefinition = "Numeric", nullable = false)
    private int quantity;
}
