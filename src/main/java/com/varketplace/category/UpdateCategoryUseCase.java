package com.varketplace.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public interface UpdateCategoryUseCase {

    void handle(UpdateCategoryCommand cmd);

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ApiModel(description = "Info to update a category")
    class UpdateCategoryCommand {

        @Setter
        @JsonIgnore
        private UUID id;

        @NotBlank
        private String name;

        private String description;
    }
}