package com.prorider.DTOs.update.store;

import com.prorider.entities.store.Size;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceBySizeUpdate {
    private int priceBySizeId;
    @ManyToOne
    @JoinColumn(name = "sizeId", nullable = false)
    private Size size;
    @Min(0)
    private BigDecimal price;
    private int productId;
    private int quantity;
}
