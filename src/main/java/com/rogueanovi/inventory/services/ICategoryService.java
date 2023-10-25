package com.rogueanovi.inventory.services;

import com.rogueanovi.inventory.model.dto.response.ApiBaseResponse;
import com.rogueanovi.inventory.model.entity.Category;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    ResponseEntity<ApiBaseResponse> findAllCategories();
    ResponseEntity<ApiBaseResponse> findCategoryById(Long id);
    ResponseEntity<ApiBaseResponse> createCategory(Category category);
    ResponseEntity<ApiBaseResponse> editCategory(Long id, Category category);
    ResponseEntity<ApiBaseResponse> deleteCategory(Long id);
}
