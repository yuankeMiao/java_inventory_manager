package com.marmotshop.inventory_manager.application.stockService.stockQueryOptions;

public enum StockSortByEnum {
    QUANTITY("quantity"),
    CREATED_TIME("createdTime"),
    UPDATED_TIME("updatedTime");

    private final String fieldName;

    StockSortByEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
