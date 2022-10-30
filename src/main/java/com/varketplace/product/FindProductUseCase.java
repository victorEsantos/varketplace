package com.varketplace.product;

import com.varketplace.product.domain.model.Product;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

public interface FindProductUseCase {

    ProductDto handle(FindProductByIdCommand cmd);

    @Value
    @AllArgsConstructor(staticName = "of")
    @ApiModel(description = "Info product id")
    class FindProductByIdCommand {

        @NotBlank
        private UUID id;
    }

    @Builder
    record ProductDto(UUID id,
                      String name,
                      String description,
                      String image,
                      BigDecimal price
                      ) {
        public static ProductDto from(Product product) {
            return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImgUrl())
                .price(product.getPrice())
                .build();
        }

    }
}