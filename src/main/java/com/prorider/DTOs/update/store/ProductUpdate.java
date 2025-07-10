package com.prorider.DTOs.update.store;

import com.prorider.entities.store.PriceBySize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ProductUpdate {
    @NotNull(message = "productId is mandatory")
    private int productId;
    @NotBlank(message = "El nombre del producto esta vacío")
    private String productName;
    private boolean isActive;
    @NotNull(message = "categoryId is mandatory")
    private int categoryId;
    @NotBlank(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "La cantidad no puede ser menor a 1")
    private int quantity;
    private boolean hasSizes;
    @NotNull(message = "La descripción no puede estar vacia")
    @Size(min = 1, max = 1000, message = "La descripción debe ser mayor a 1 y menor a 1000 caracteres")
    private String description;
    /// Puede ser Null o 0 por las tallas
    private BigDecimal price;

    private List<PriceBySize> pricesBySize = new ArrayList<>();
}
