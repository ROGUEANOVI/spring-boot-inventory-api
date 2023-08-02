package com.rogueanovi.inventory.response;

import com.rogueanovi.inventory.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private List<Category> categories;
}
