package com.blog.blog_app.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotBlank
    @Size(min=4 ,message = "Minimum 4 characters")
    private String categoryTitle;
    @Size(min =10 , message = "Minimum 10 characters")
    private String categoryDescription;
}
