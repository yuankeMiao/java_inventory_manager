package com.marmotshop.inventory_manager.infrastructure.services.scheduled;

import java.util.UUID;

public record TrendingProduct(UUID productId,
        long quantity) {
}
