package com.rogueanovi.inventory.controller;

import com.rogueanovi.inventory.response.CategoryResponseRest;
import com.rogueanovi.inventory.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("categories")
    public ResponseEntity<CategoryResponseRest> searchCategories (){
        return categoryService.search();
    }
}
