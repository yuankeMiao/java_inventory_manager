package com.marmotshop.inventory_manager.application.shared;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseQueryOptions {
    private int page;
    private int limit;

    @Enumerated(EnumType.STRING)
    private OrderByEnum orderBy = OrderByEnum.ASC;
}
