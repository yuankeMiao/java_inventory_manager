package com.marmotshop.inventory_manager.application.orderService.orderQueryOptions;

public enum OrderSortByEnum {
    CREATED_TIME("createdTime"),
    UPDATED_TIME("updatedTime");

    private final String fieldName;

    OrderSortByEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
