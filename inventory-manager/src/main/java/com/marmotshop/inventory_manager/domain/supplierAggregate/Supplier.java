package com.marmotshop.inventory_manager.domain.supplierAggregate;

import com.marmotshop.inventory_manager.domain.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
