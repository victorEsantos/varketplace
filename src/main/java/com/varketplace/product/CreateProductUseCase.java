package com.varketplace.product;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public interface CreateProductUseCase {

    UUID handle(CreateProductCommand cmd);

    @Value
    @Builder
    @ApiModel(description = "Info to create a new product")
    class CreateProductCommand {

        @NotBlank
        private String name;

        private String description;

        @NotBlank
        private BigDecimal price;

        @NotBlank
        private String imgUrl;

        private Set<UUID> categories;

    }
}