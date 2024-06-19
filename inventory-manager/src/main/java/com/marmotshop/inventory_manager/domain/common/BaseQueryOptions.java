package com.marmotshop.inventory_manager.domain.common;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseQueryOptions {
    private int page;
    private int limit;

    @Enumerated(EnumType.STRING)
    private OrderByEnum orderBy = OrderByEnum.ASC;
}
