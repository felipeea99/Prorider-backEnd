package com.prorider.interfaces.store;

import com.prorider.DTOs.response.store.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface ICategoryStore {
    List<CategoryResponse> findCategoriesByStoreId(UUID storeId);
    Boolean deleteCategoryByID(int categoryStoreId);
    CategoryResponse addCategoryStore(CategoryStoreRequest categoryStoreRequest );
    CategoryStore ToCategoryStore(CategoryStoreRequest request);

    }
