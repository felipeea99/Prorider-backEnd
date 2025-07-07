package com.prorider.interfaces.store;

import com.prorider.DTOs.request.store.CategoryRequest;
import com.prorider.DTOs.response.store.CategoryResponse;
import com.prorider.DTOs.update.store.CategoryUpdate;
import com.prorider.entities.store.Category;

import java.util.List;

public interface ICategory {
    List<CategoryResponse> findAllCategories();
    CategoryResponse findCategoryByID(int categoryID);
    Boolean deleteCategory(int categoryID);
    CategoryResponse addCategory(CategoryRequest categoryRequest );
    CategoryResponse editCategory(CategoryUpdate categoryUpdate);
    Category findCategoryByIdBaseForm(int categoryID);
    CategoryResponse ToCategoryResponse(Category category);
}
