package com.rogueanovi.inventory.dao;

import com.rogueanovi.inventory.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDao {
    List<Product> findAllProducts();
    Optional<Product> findProductById(Long id);
    Product saveProduct(Product product);
    void deleteProductById(Long id);
}
