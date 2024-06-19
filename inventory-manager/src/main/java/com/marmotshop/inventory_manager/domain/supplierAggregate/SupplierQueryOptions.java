package com.marmotshop.inventory_manager.domain.supplierAggregate;

import java.util.UUID;

import com.marmotshop.inventory_manager.domain.common.BaseQueryOptions;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierQueryOptions extends BaseQueryOptions {
    private UUID productId;
    private String name;

    @Enumerated(EnumType.STRING)
    private SupplierSortByEnum sortBy = SupplierSortByEnum.NAME;
}
