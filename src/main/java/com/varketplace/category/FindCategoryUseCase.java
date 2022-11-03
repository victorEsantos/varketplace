package com.varketplace.category;

import com.varketplace.category.domain.model.Category;
import com.varketplace.product.FindProductUseCase;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public interface FindCategoryUseCase {

    CategoryDto handle(FindCategoryByIdCommand cmd);

    Page<CategoryDto> handle(FindAllCategoryCommand of);

    @Value
    @AllArgsConstructor(staticName = "of")
    @ApiModel(description = "Info category id")
    class FindCategoryByIdCommand {

        @NotBlank
        private UUID id;
    }

    @Value
    @AllArgsConstructor(staticName = "of")
    @ApiModel(description = "All categories")
    class FindAllCategoryCommand {
        Pageable pageable;

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