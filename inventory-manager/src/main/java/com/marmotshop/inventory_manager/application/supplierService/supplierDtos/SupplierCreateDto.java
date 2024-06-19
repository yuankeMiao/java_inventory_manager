package com.marmotshop.inventory_manager.application.supplierService.supplierDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierCreateDto {
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Size(min = 2, max = 50)
    private String contactPerson;

    @NotNull
    @Size(min = 2, max = 100)
    private String contactEmail;

    @NotNull
    @Size(min = 2, max = 255)
    private String address;
}
