package com.electronic.store.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriesDto {


    private String categoryId;

    @NotBlank(message = "Title must be minimum 4 characters!!")
    @Size(min = 4, message = "Title must be minimum 4 characters!!")
    private String title;

    @NotBlank(message = "Description required!!")
    @Size(min = 10, max = 15, message = "Description must be between 10 and 15 characters!!")
    private String description;


}
