package com.rogueanovi.inventory.model.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditCategoryDto {
    private String name;
    private String description;
}
