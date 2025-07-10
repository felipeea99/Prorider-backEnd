package com.prorider.DTOs.update.store;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeUpdate {
    private int sizeId;
    @NotNull(message = "Talla esta vacía")
    private String size;
}
