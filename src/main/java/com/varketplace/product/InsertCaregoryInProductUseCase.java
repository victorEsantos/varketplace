package com.varketplace.product;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public interface InsertCaregoryInProductUseCase {

    void handle(InsertCaregoryOnProductCommand cmd, UUID productId);

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ApiModel(description = "Insert one more category in product")
    class InsertCaregoryOnProductCommand {

        @NotBlank
        private String categoryId;

    }
}