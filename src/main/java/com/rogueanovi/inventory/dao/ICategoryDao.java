package com.rogueanovi.inventory.dao;

import com.rogueanovi.inventory.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDao extends CrudRepository<Category, Long > {
}
