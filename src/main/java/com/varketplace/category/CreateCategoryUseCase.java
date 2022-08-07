package com.varketplace.category;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

public interface CreateCategoryUseCase {

    UUID handle(CreateCategoryCommand cmd);

    @Value
    @Builder
    @ApiModel(description = "Info to create a new category")
    class CreateCategoryCommand {

        @NotBlank
        private String name;

        private String description;
    }
}