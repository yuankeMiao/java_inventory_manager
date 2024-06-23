package com.marmotshop.inventory_manager.application.supplierService.supplierQueryOptions;

public enum SupplierSortByEnum {
    NAME("name"), CREATED_TIME("createdTime"), UPDATED_TIME("updatedTime");

    private final String fieldName;

    SupplierSortByEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
