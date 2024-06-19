package com.marmotshop.inventory_manager.application.supplierService.supplierDtos;

import com.marmotshop.inventory_manager.domain.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierReadDto extends BaseEntity {
    private String name;
    private String contactPerson;
    private String contactEmail;
    private String address;
}
