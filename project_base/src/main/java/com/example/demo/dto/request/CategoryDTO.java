package com.example.demo.dto.request;

import javax.validation.constraints.NotBlank;

public class CategoryDTO {

    @NotBlank(message = "categoryName is not null or empty")
    private String categoryName;

    private String description;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
