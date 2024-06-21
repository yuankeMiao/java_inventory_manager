package com.marmotshop.inventory_manager.application.supplierService.supplierDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierCreateDto {
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50)
    private String name;

    @NotNull(message = "Contact person cannot be null")
    @Size(min = 2, max = 50)
    private String contactPerson;

    @NotNull(message = "Contact email cannot be null")
    @Size(min = 2, max = 100)
    private String contactEmail;

    @NotNull(message = "Address cannot be null")
    @Size(min = 2, max = 255)
    private String address;
}
