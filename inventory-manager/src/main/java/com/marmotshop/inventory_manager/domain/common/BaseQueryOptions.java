package com.marmotshop.inventory_manager.domain.common;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseQueryOptions {
    public int page;
    public int limit;

    @Enumerated(EnumType.STRING)
    public OrderByEnum orderBy = OrderByEnum.ASC;
}
