package com.marmotshop.inventory_manager.presentation.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseEntity {
    @Builder.Default
    public final Object data = null;

    public List<ErrorEntity> errors;
}
