package com.rogueanovi.inventory.controller;

import com.rogueanovi.inventory.response.dto.CategoryResponseDto;
import com.rogueanovi.inventory.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * Get all categories
     * @return
     */
    @GetMapping()
    public ResponseEntity<CategoryResponseDto> searchAllCategories (){
        return categoryService.searchAllCategories();
    }

    /**
     * Get category by id
     * @Param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> SearchCategoryById(@PathVariable("id") Long id){
        return categoryService.searchCategoryById(id);
    }
}
