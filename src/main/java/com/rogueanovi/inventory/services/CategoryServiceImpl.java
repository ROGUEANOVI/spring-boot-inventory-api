package com.rogueanovi.inventory.services;

import com.rogueanovi.inventory.dao.ICategoryDao;
import com.rogueanovi.inventory.model.Category;
import com.rogueanovi.inventory.response.dto.CategoryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseDto> searchAllCategories() {
        CategoryResponseDto response = new CategoryResponseDto();
        try {
            List<Category> categories = (List<Category>) categoryDao.findAll();
            response.getCategoryResponseDto().setCategories(categories);
            response.setMetadata("Ok", "200", "Successful Query");
        } catch (Exception e){
            response.setMetadata("Error", "500", "Error when consulting the categories");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseDto> searchCategoryById(Long id) {
        CategoryResponseDto response = new CategoryResponseDto();
        List<Category> categories = new ArrayList<>();
        try {
            Optional<Category> category = categoryDao.findById(id);
            if (category.isPresent()){
                categories.add(category.get());
                response.getCategoryResponseDto().setCategories(categories);
                response.setMetadata("Ok", "200", "Successful Query");
            } else {
                response.setMetadata("Not Found", "404", "Category not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            response.setMetadata("Error", "500", "Error querying by id");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
