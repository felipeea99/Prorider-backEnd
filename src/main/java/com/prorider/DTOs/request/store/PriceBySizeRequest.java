package com.prorider.DTOs.request.store;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceBySizeRequest {
    private int productId;
    @ManyToOne
    @JoinColumn(name = "sizeId", nullable = false)
    private int sizeId;
    @Min(value = 1, message = "La precio no puede ser menor a 1")
    @NotNull(message = "El precio no puede estar vacío")
    private BigDecimal price;
    @Min(1)
    @NotNull(message = "La cantidad no puede estar vacia")
    private int quantity;
}
