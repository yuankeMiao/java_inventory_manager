package com.marmotshop.inventory_manager.presentation.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.marmotshop.inventory_manager.application.shared.ResponsePage;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponseEntity<T> {
    public ResponsePage<T> data;
    
    @Builder.Default
    public final Object errors = null;
}
