package com.rogueanovi.inventory.response.dto;

import com.rogueanovi.inventory.response.ApiBaseResponse;
import com.rogueanovi.inventory.response.CategoryResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto extends ApiBaseResponse {
    private CategoryResponse categoryResponseDto = new CategoryResponse();

}
