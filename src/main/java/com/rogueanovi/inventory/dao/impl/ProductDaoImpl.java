package com.rogueanovi.inventory.dao.impl;

import com.rogueanovi.inventory.dao.IProductDao;
import com.rogueanovi.inventory.model.entity.Product;
import com.rogueanovi.inventory.repository.IProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements IProductDao {

    private final IProductRepository productRepository;

    public ProductDaoImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
