package com.rogueanovi.inventory.model.dto.request;


import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDto {
    private String name;

    private BigDecimal price;

    private Long amount;

    private byte[] picture;

    private Long categoryId;
}
