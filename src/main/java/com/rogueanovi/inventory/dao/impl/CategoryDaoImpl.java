package com.rogueanovi.inventory.dao.impl;

import com.rogueanovi.inventory.dao.ICategoryDao;
import com.rogueanovi.inventory.model.Category;
import com.rogueanovi.inventory.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CategoryDaoImpl implements ICategoryDao {

    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}