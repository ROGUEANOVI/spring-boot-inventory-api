package com.rogueanovi.inventory.services.impl;

import com.rogueanovi.inventory.dao.ICategoryDao;
import com.rogueanovi.inventory.dao.IProductDao;
import com.rogueanovi.inventory.model.dto.response.ApiBaseResponse;
import com.rogueanovi.inventory.model.dto.response.ProductDto;
import com.rogueanovi.inventory.model.entity.Category;
import com.rogueanovi.inventory.model.entity.Product;
import com.rogueanovi.inventory.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    private final ICategoryDao categoryDao;
    private final IProductDao productDao;

    public ProductServiceImpl(ICategoryDao categoryDao, IProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ApiBaseResponse> findAllproducts() {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            List<Product> productsEntity = productDao.findAllProducts();
            var products = productsEntity.stream().map(productEntity ->
                    ProductDto.builder()
                        .id(productEntity.getId())
                        .name(productEntity.getName())
                        .price(productEntity.getPrice())
                        .amount(productEntity.getAmount())
                        .picture(productEntity.getPicture())
                        .category(productEntity.getCategory())
                        .build())
                    .collect(Collectors.toList());

            response.setData(products);
            response.setMetadata("200", "Ok", "Successful Query");
        } catch (Exception e){
            response.setMetadata("500", "Internal Server Error", "Error when consulting the products: " + e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ApiBaseResponse> findProductById(Long id) {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            Optional<Product> productEntity = productDao.findProductById(id);
            if (productEntity.isPresent()){
                var productDto = ProductDto
                        .builder()
                        .id(productEntity.get().getId())
                        .name(productEntity.get().getName())
                        .price(productEntity.get().getPrice())
                        .amount(productEntity.get().getAmount())
                        .picture(productEntity.get().getPicture())
                        .category(productEntity.get().getCategory())
                        .build();

                response.setData(productDto);
                response.setMetadata("200", "Ok", "Successful Query");
            } else {
                response.setMetadata("404", "Not Found", "Product not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            response.setMetadata("500", "Internal Server Error", "Error querying by id: " + e.getMessage() );
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiBaseResponse> createProduct(Product product, Long categoryId) {
        ApiBaseResponse response = new ApiBaseResponse();
        try{
            Optional<Category> categoryEntity = categoryDao.findCategoryById(categoryId);
            if (categoryEntity.isPresent()){
                product.setCategory(categoryEntity.get());
            }else {
                response.setMetadata("404", "Not Found", "Category associated with the product not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            Product productEntity = productDao.saveProduct(product);

            var productDto = ProductDto
                    .builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .price(productEntity.getPrice())
                    .amount(productEntity.getAmount())
                    .picture(productEntity.getPicture())
                    .category(productEntity.getCategory())
                    .build();

            response.setData(productDto);
            response.setMetadata("201", "Ok", "Successful Query");
        } catch (Exception e){
            response.setMetadata("500", "Internal Server Error", "Error saving product: " + e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Override
    @Transactional
    public ResponseEntity<ApiBaseResponse> editProduct(Long id, Product product, Long categoryId) {
        ApiBaseResponse response = new ApiBaseResponse();
        try{
            Optional<Product> productEntity = productDao.findProductById(id);
            if (productEntity.isPresent()){
                productEntity.get().setName(product.getName());
                productEntity.get().setPrice(product.getPrice());
                productEntity.get().setAmount(product.getAmount());
                productEntity.get().setPicture(product.getPicture());

                Optional<Category> categoryEntity = categoryDao.findCategoryById(categoryId);
                if (categoryEntity.isPresent()){
                    productEntity.get().setCategory(categoryEntity.get());
                }

                Product updatedProduct = productDao.saveProduct(productEntity.get());
                var productDto = ProductDto.builder()
                        .id(updatedProduct.getId())
                        .name(updatedProduct.getName())
                        .price(updatedProduct.getPrice())
                        .amount(updatedProduct.getAmount())
                        .picture(updatedProduct.getPicture())
                        .category(updatedProduct.getCategory())
                        .build();

                response.setData(productDto);
                response.setMetadata("200", "Ok", "Successful Update");
            } else {
                response.setMetadata("404", "Not Found", "Product not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            response.setMetadata("500", "Internal Server Error", "Error updating product: " + e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiBaseResponse> deleteProduct(Long id) {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            Optional<Product> productEntity = productDao.findProductById(id);
            if (productEntity.isPresent()){
                productDao.deleteProductById(id);
                response.setMetadata("200", "Ok", "Successful delete");
            } else {
                response.setMetadata("404", "Not Found", "Product not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            response.setMetadata("500", "Internal Server Error", "Error querying by id: " + e.getMessage() );
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
