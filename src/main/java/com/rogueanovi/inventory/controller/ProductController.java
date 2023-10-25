package com.rogueanovi.inventory.controller;

import com.rogueanovi.inventory.model.dto.response.ApiBaseResponse;
import com.rogueanovi.inventory.model.entity.Product;
import com.rogueanovi.inventory.services.IProductService;
import com.rogueanovi.inventory.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/api/v1/product")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    /**
     * Get all products
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<ApiBaseResponse> getAllProducts (){
        return productService.findAllproducts();
    }


    /**
     * Get product by id
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiBaseResponse> getProductById(@PathVariable("id") Long id){
        return productService.findProductById(id);
    }


    /**
     * Create Product
     * @param name
     * @param price
     * @param amount
     * @param picture
     * @param categoryId
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<ApiBaseResponse> createProduct(
            @RequestParam("name") String name,
            @RequestParam("price") BigDecimal price,
            @RequestParam("amount") Long amount,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("categoryId") Long categoryId
            )throws IOException {

        Product product = Product.builder()
                .name(name)
                .price(price)
                .amount(amount)
                .picture(Util.compressZLib(picture.getBytes()))
                .build();

        return productService.createProduct(product, categoryId);
    }


    /**
     * Update Product
     * @param id
     * @param name
     * @param price
     * @param amount
     * @param picture
     * @param categoryId
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiBaseResponse> updateProduct(
        @PathVariable("id") Long id,
        @RequestParam("name") String name,
        @RequestParam("price") BigDecimal price,
        @RequestParam("amount") Long amount,
        @RequestParam("picture") MultipartFile picture,
        @RequestParam("categoryId") Long categoryId) throws IOException {

        Product product = Product.builder()
                .name(name)
                .price(price)
                .amount(amount)
                .picture(Util.compressZLib(picture.getBytes()))
                .build();

        return productService.editProduct(id, product, categoryId);
    }


    /**
     * Delete Product
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ApiBaseResponse> deleteCategory(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
