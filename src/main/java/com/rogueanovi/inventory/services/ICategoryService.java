package com.rogueanovi.inventory.services;

import com.rogueanovi.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    ResponseEntity<CategoryResponseRest> search();
}
