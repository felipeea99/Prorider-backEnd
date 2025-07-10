package com.prorider.DTOs.request.store;

import com.prorider.DTOs.response.store.SizeResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    @NotNull(message = "La cantidad esta vacia")
    @Min(value = 1, message = "La cantidad minima debe ser 1")
    private Integer quantity;
    private boolean isCompleted;
    @NotNull(message = "El productId no debe estar vacio")
    private int productId;
    /// Might be true or false
    @NotNull(message = "El campo 'isSize' no debe estar vacio")
    private Boolean isSize;
    /// Might be Null
    private SizeResponse sizeObj;
    @NotNull(message = "El 'userId' no debe estar vacio")
    private UUID userId;
}
