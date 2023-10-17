package com.rogueanovi.inventory.repository;

import com.rogueanovi.inventory.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface ICategoryRepository extends CrudRepository<Category, Long > {
}
