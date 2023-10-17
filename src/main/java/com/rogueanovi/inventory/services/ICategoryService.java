package com.rogueanovi.inventory.services;

import com.rogueanovi.inventory.model.Category;
import com.rogueanovi.inventory.response.dto.CategoryResponseDto;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    ResponseEntity<CategoryResponseDto> findAllCategories();
    ResponseEntity<CategoryResponseDto> findCategoryById(Long id);
    ResponseEntity<CategoryResponseDto> createCategory(Category category);
    ResponseEntity<CategoryResponseDto> editCategory(Long id, Category category);
    ResponseEntity<CategoryResponseDto> deleteCategory(Long id);
}
