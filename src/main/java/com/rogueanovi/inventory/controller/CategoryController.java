package com.rogueanovi.inventory.controller;

import com.rogueanovi.inventory.model.dto.request.AddCategoryDto;
import com.rogueanovi.inventory.model.dto.request.EditCategoryDto;
import com.rogueanovi.inventory.model.dto.response.ApiBaseResponse;
import com.rogueanovi.inventory.model.entity.Category;
import com.rogueanovi.inventory.services.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/category")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Get Categories
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<ApiBaseResponse> getAllCategories (){
        return categoryService.findAllCategories();
    }


    /**
     * Get Category by Id
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiBaseResponse> getCategoryById(@PathVariable("id") Long id){
        return categoryService.findCategoryById(id);
    }

    /**
     * Create Category
     * @param categoryDto
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<ApiBaseResponse> createCategory(@RequestBody AddCategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return categoryService.createCategory(category);
    }

    /**
     * Update Category
     * @param id
     * @param categoryDto
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiBaseResponse> updateCategory(@PathVariable("id") Long id, @RequestBody EditCategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return categoryService.editCategory(id, category);
    }

    /**
     * Delete Category
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ApiBaseResponse> deleteCategory(@PathVariable("id") Long id){
        return categoryService.deleteCategory(id);
    }
}
