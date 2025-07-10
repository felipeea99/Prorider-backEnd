package com.prorider.DTOs.update.store;

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
public class CartUpdate {

    @Min(value = 1, message = "El 'cartId' debe ser mayor o igual a 1")
    private int cartId;
    @NotNull(message = "La cantidad está vacía")
    @Min(value = 1, message = "La cantidad mínima debe ser 1")
    private Integer quantity;
    private boolean isCompleted;
    @NotNull(message = "El campo 'productId' no debe estar vacío")
    private Integer productId;
    @NotNull(message = "El campo '¿Tiene talla?' no debe estar vacío")
    private Boolean isSize;
    /// Puede ser null si isSize == false
    private SizeResponse sizeObj;
    @NotNull(message = "El campo 'userId' no debe estar vacío")
    private UUID userId;
}
