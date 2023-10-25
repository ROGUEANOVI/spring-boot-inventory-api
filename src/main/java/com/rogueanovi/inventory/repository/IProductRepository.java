package com.rogueanovi.inventory.repository;

import com.rogueanovi.inventory.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {
}
