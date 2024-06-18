package com.marmotshop.inventory_manager.domain.supplierAggregate;

import java.util.UUID;

import com.marmotshop.inventory_manager.domain.common.BaseQueryOptions;

public class SupplierQuesryOptions extends BaseQueryOptions {
    public UUID priductId;
    public String name;
    public SupplierSortByEnum sortBy = SupplierSortByEnum.NAME;
}
