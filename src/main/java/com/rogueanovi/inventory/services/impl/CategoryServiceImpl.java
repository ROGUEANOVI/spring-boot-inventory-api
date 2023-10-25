package com.rogueanovi.inventory.services.impl;

import com.rogueanovi.inventory.dao.ICategoryDao;
import com.rogueanovi.inventory.model.dto.response.ApiBaseResponse;
import com.rogueanovi.inventory.model.entity.Category;
import com.rogueanovi.inventory.model.dto.response.CategoryDto;
import com.rogueanovi.inventory.services.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryDao categoryDao;

    public CategoryServiceImpl(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ApiBaseResponse> findAllCategories() {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            List<Category> categoriesEntity = categoryDao.findAllCategories();
            var categories = categoriesEntity.stream().map(categoryEntity -> CategoryDto.builder()
                    .id(categoryEntity.getId())
                    .name(categoryEntity.getName())
                    .description(categoryEntity.getDescription())
                    .build())
                    .collect(Collectors.toList());

            response.setData(categories);
            response.setMetadata("200", "Ok", "Successful Query");
        } catch (Exception e){
            response.setMetadata("500", "Internal Server Error", "Error when consulting the categories: " + e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ApiBaseResponse> findCategoryById(Long id) {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            Optional<Category> categoryEntity = categoryDao.findCategoryById(id);
            if (categoryEntity.isPresent()){
                var categoryDto = CategoryDto
                        .builder()
                        .id(categoryEntity.get().getId())
                        .name(categoryEntity.get().getName())
                        .description(categoryEntity.get().getDescription())
                        .build();

                response.setData(categoryDto);
                response.setMetadata("200", "Ok", "Successful Query");
            } else {
                response.setMetadata("404", "Not Found", "Category not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            response.setMetadata("500", "Internal Server Error", "Error querying by id: " + e.getMessage() );
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiBaseResponse> createCategory(Category category) {
        ApiBaseResponse response = new ApiBaseResponse();
        try{
            Category categoryEntity = categoryDao.saveCategory(category);

            var categoryDto = CategoryDto
                    .builder()
                    .id(categoryEntity.getId())
                    .name(categoryEntity.getName())
                    .description(categoryEntity.getDescription())
                    .build();

            response.setData(categoryDto);
            response.setMetadata("201", "Created", "Successful Query");
        } catch (Exception e){
            response.setMetadata("500", "Internal Server Error", "Error saving category: " + e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiBaseResponse> editCategory(Long id, Category category) {
        ApiBaseResponse response = new ApiBaseResponse();
        try{
            Optional<Category> categoryEntity = categoryDao.findCategoryById(id);
            if (categoryEntity.isPresent()){
                categoryEntity.get().setName(category.getName());
                categoryEntity.get().setDescription(category.getDescription());

                Category updatedCategory = categoryDao.saveCategory(categoryEntity.get());

                var categoryDto = CategoryDto.builder()
                    .id(updatedCategory.getId())
                    .name(updatedCategory.getName())
                    .description(updatedCategory.getDescription())
                    .build();

                response.setData(categoryDto);
                response.setMetadata("200", "Ok", "Successful Update");
            } else {
                response.setMetadata("404", "Not Found", "Category not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            response.setMetadata("500", "Internal Server Error", "Error updating category :" + e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiBaseResponse> deleteCategory(Long id) {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            Optional<Category> categoryEntity = categoryDao.findCategoryById(id);
            if (categoryEntity.isPresent()){
                categoryDao.deleteCategoryById(id);
                response.setMetadata("200", "Ok", "Successful delete");
            } else {
                response.setMetadata("404", "Not Found", "Category not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            response.setMetadata("500", "Internal Server Error", "Error querying by id: " + e.getMessage() );
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
