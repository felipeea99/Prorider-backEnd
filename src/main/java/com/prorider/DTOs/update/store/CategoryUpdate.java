package com.prorider.DTOs.update.store;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdate {
    @Min(value = 1, message = "El 'categoryId' esta vacio")
    private int categoryId;
    @NotBlank(message = "El nombre de la categoria esta vacío")
    private String categoryName;
}
