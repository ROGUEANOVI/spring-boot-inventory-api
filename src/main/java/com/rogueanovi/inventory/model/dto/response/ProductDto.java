package com.rogueanovi.inventory.model.dto.response;

import com.rogueanovi.inventory.model.entity.Category;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;

    private String name;

    private BigDecimal price;

    private Long amount;

    private byte[] picture;

    private Category category;
}
