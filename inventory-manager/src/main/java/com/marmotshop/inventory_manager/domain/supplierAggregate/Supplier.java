package com.marmotshop.inventory_manager.domain.supplierAggregate;

import com.marmotshop.inventory_manager.domain.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String contactPerson;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String contactEmail;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String address;
}
