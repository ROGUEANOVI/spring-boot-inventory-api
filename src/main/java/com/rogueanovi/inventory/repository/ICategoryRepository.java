package com.rogueanovi.inventory.repository;

import com.rogueanovi.inventory.model.entity.Category;
import org.springframework.data.repository.CrudRepository;


public interface ICategoryRepository extends CrudRepository<Category, Long > {
}
