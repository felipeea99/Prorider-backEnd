package com.prorider.DTOs.request.store;

import com.prorider.entities.store.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    @NotBlank(message = "El nombre de categoria no debe estar vacio")
    private String categoryName;

    public Category ToCategoryObj(){
        Category category = new Category();
        category.setCategoryName(this.categoryName);
        return category;
    }
}
