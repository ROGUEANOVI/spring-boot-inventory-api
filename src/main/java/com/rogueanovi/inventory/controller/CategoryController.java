package com.rogueanovi.inventory.controller;

import com.rogueanovi.inventory.dto.AddCategoryDto;
import com.rogueanovi.inventory.dto.EditCategoryDto;
import com.rogueanovi.inventory.model.Category;
import com.rogueanovi.inventory.response.dto.CategoryResponseDto;
import com.rogueanovi.inventory.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/category")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * Get all categories
     * @return
     */
    @GetMapping()
    public ResponseEntity<CategoryResponseDto> getAllCategories (){
        return categoryService.findAllCategories();
    }

    /**
     * Get category by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable("id") Long id){
        return categoryService.findCategoryById(id);
    }

    /**
     * Add category
     * @param categoryDto
     * @return
     */
    @PostMapping()
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody AddCategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return categoryService.createCategory(category);
    }

    /**
     * Update category
     * @param id
     * @param categoryDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable("id") Long id, @RequestBody EditCategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return categoryService.editCategory(id, category);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<CategoryResponseDto> deleteCategory(@PathVariable("id") Long id){
        return categoryService.deleteCategory(id);
    }
}
