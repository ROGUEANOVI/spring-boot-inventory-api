package com.rogueanovi.inventory.services;

import com.rogueanovi.inventory.model.dto.response.ApiBaseResponse;
import com.rogueanovi.inventory.model.entity.Product;
import org.springframework.http.ResponseEntity;

public interface IProductService {
    ResponseEntity<ApiBaseResponse> findAllproducts();
    ResponseEntity<ApiBaseResponse> findProductById(Long id);
    ResponseEntity<ApiBaseResponse> createProduct(Product product, Long categoryId);
    ResponseEntity<ApiBaseResponse> editProduct(Long id, Product product, Long categoryId);
    ResponseEntity<ApiBaseResponse> deleteProduct(Long id);
}
