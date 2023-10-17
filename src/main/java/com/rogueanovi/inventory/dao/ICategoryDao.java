package com.rogueanovi.inventory.dao;

import com.rogueanovi.inventory.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryDao {
    List<Category> findAllCategories();
    Optional<Category> findCategoryById(Long id);
    Category saveCategory(Category category);
    void deleteCategoryById(Long id);
}
