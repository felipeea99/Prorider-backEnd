package com.prorider.services.store;

import com.prorider.DTOs.request.store.CategoryRequest;
import com.prorider.DTOs.response.store.CategoryResponse;
import com.prorider.DTOs.update.store.CategoryUpdate;
import com.prorider.entities.store.Category;
import com.prorider.exceptions.BadRequestException;
import com.prorider.interfaces.store.ICategory;
import com.prorider.repository.store.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService implements ICategory {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    ///Get all the categories from the database ADMIN
    @Override
    public List<CategoryResponse> findAllCategories() {
        //Retrieve all the categories objects from the database
        List<Category> categoryList = categoryRepository.findAll();
        //Return it and transform it into CategoryResponse type
        return categoryList.stream().map(CategoryResponse::ToCategoryResponseObj).toList();
    }
    ///Get a category from the database by a categoryId
    @Override
    public CategoryResponse findCategoryByID(int categoryId) {
        //CategoryId validation
       Category category = findCategoryByIdBaseForm(categoryId);
        //Convert the Category object into CategoryResponse obj
       return CategoryResponse.ToCategoryResponseObj(category);

    }

    ///Delete a category from the database by a categoryId
    @Override
    public Boolean deleteCategory(int categoryId) {
        Category category = findCategoryByIdBaseForm(categoryId);
        categoryRepository.deleteById(categoryId);
        return true;
    }

    ///Adds a category from the database
    @Transactional
    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        //Convert the categoryRequest into Category Object
        Category category = categoryRequest.ToCategoryObj();
        //Stores it and saved it into the "categorySaved" variable
        Category categorySaved =  this.categoryRepository.save(category);
       //Convert the savedObject into CategoryResponse
        return CategoryResponse.ToCategoryResponseObj(categorySaved);
    }

    ///Edits  a category from the database base on a categoryId
    @Override
    public CategoryResponse editCategory(CategoryUpdate categoryUpdate) {
        // find the CategoryObj to Edit on the database base on the categoryId
        int categoryId = categoryUpdate.getCategoryId();
         Category category = findCategoryByIdBaseForm(categoryId);
         //Edit the changes and save them and stores it into the categorySaved variable
        category.setCategoryName(categoryUpdate.getCategoryName());
        Category categorySaved= categoryRepository.save(category);
        //Convert categorySaved into CategoryResponse object
        return CategoryResponse.ToCategoryResponseObj(categorySaved);

    }
    ///Retrieve a categoryObject from the database base on the categoryId provided
    @Override
    public Category findCategoryByIdBaseForm(int categoryID) {
        return categoryRepository.findById(categoryID)
                .orElseThrow(() -> new BadRequestException("CategoryId not found on the database"));
    }

    @Override
    public CategoryResponse ToCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryId(category.getCategoryId());
        categoryResponse.setCategoryName(categoryResponse.getCategoryName());
        return categoryResponse;
    }


}
