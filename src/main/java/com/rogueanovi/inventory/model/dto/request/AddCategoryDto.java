package com.rogueanovi.inventory.model.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryDto {
    private String name;
    private String description;
}
