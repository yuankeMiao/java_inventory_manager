package com.marmotshop.inventory_manager.application.supplierService.supplierQueryOption;

import java.util.UUID;

import com.marmotshop.inventory_manager.application.shared.BaseQueryOptions;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierQueryOptions extends BaseQueryOptions {
    private UUID productId;
    private String name;

    @Enumerated(EnumType.STRING)
    private SupplierSortByEnum sortBy = SupplierSortByEnum.NAME;
}
