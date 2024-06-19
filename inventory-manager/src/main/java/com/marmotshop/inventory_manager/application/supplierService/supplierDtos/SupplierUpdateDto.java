package com.marmotshop.inventory_manager.application.supplierService.supplierDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierUpdateDto {
    private String name;
    private String contactPerson;
    private String contactEmail;
    private String address;
}
