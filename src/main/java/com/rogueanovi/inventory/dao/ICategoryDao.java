package com.rogueanovi.inventory.dao;

import com.rogueanovi.inventory.model.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ICategoryDao {
    List<Category> findAllCategories();
    Optional<Category> findCategoryById(Long id);
    Category saveCategory(Category category);
    void deleteCategoryById(Long id);
}
