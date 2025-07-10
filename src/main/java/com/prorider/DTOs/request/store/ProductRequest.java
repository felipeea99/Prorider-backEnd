package com.prorider.DTOs.request.store;

import com.prorider.DTOs.response.store.PriceBySizeResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = "El nombre del producto esta vacío")
    private String productName;
    private boolean isActive;
    @NotNull(message = "storeId  esta vacío")
    private UUID storeId;
    @NotNull(message = "categoryId esta vacío")
    private int categoryId;
    @NotNull(message = "La cantidad esta vacia")
    @Min(value = 1 , message = "La cantidad no debe ser menor a 1 ")
    private int quantity;
    @NotNull(message = " 'Es Talla' esta vacío")
    private boolean hasSizes;
    @Min(value = 1, message = "El precio no debe ser menor a 1")
    @NotNull(message = "El precio esta vacío")
    private BigDecimal price;
    @NotNull(message = "La descripción esta vacia")
    @Size(min = 1,max = 1000, message = "La descripción no puede tener más de 500 caracteres")
    private String description;

    private List<PriceBySizeResponse> pricesBySize = new ArrayList<>();
}
