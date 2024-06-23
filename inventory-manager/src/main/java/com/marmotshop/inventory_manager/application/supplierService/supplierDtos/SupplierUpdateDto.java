package com.marmotshop.inventory_manager.application.supplierService.supplierDtos;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierUpdateDto {
    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 50)
    private String contactPerson;

    @Size(min = 2, max = 100)
    private String contactEmail;

    @Size(min = 2, max = 255)
    private String address;
}
