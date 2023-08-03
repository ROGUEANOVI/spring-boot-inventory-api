package com.rogueanovi.inventory.services;

import com.rogueanovi.inventory.model.Category;
import com.rogueanovi.inventory.response.dto.CategoryResponseDto;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    ResponseEntity<CategoryResponseDto> searchAllCategories();
    ResponseEntity<CategoryResponseDto> searchCategoryById(Long id);
    ResponseEntity<CategoryResponseDto> addCategory(Category category);
    ResponseEntity<CategoryResponseDto> editCategory(Long id, Category category);
    ResponseEntity<CategoryResponseDto> deleteCategory(Long id);
}
