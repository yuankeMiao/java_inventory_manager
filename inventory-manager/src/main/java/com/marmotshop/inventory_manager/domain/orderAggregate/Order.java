package com.marmotshop.inventory_manager.domain.orderAggregate;

import java.util.HashSet;
import java.util.Set;

import com.marmotshop.inventory_manager.domain.common.BaseEntity;
import com.marmotshop.inventory_manager.domain.orderItemAggregate.OrderItem;
import com.marmotshop.inventory_manager.domain.supplierAggregate.Supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn
    private Supplier supplier;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Transient
    private Set<OrderItem> orderItems = new HashSet<>();
}
