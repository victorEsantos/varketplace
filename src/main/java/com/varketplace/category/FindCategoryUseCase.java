package com.varketplace.category;

import com.varketplace.category.domain.model.Category;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public interface FindCategoryUseCase {

    CategoryDto handle(FindCategoryByIdCommand cmd);

    @Value
    @AllArgsConstructor(staticName = "of")
    @ApiModel(description = "Info category id")
    class FindCategoryByIdCommand {

        @NotBlank
        private UUID id;
    }

    @Builder
    @ApiModel(description = "Info category id")
    record CategoryDto(UUID id,
                       String name,
                       String description) {

        public static CategoryDto from(Category category) {
            return CategoryDto
                .builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
        }
    }
}