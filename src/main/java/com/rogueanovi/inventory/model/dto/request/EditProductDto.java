package com.rogueanovi.inventory.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditProductDto {
    private String name;

    private BigDecimal price;

    private Long amount;

    private byte[] picture;

    private Long categoryId;
}
