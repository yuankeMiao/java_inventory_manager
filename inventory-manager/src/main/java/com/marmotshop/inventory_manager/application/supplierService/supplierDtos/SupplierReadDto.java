package com.marmotshop.inventory_manager.application.supplierService.supplierDtos;

import java.time.LocalDateTime;
import java.util.UUID;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierReadDto {
    private UUID id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String name;
    private String contactPerson;
    private String contactEmail;
    private String address;
}
