package com.varketplace.product.service;

import com.varketplace.category.domain.CategoryDomainRepository;
import com.varketplace.category.domain.model.Category;
import com.varketplace.product.CreateProductUseCase;
import com.varketplace.product.domain.ProductDomainRepository;
import com.varketplace.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {

    private final ProductDomainRepository repository;
    private final CategoryDomainRepository categoryRepository;

    @Override
    public UUID handle(CreateProductCommand cmd) {

        var product = Product
            .builder()
            .id(UUID.randomUUID())
            .name(cmd.getName())
            .description(cmd.getDescription())
            .price(cmd.getPrice())
            .imgUrl(cmd.getImgUrl())
            .categories(getCategories(cmd.getCategories()))
            .build();

        repository.save(product);

        return product.getId();
    }

    private Set<Category> getCategories(Set<UUID> categories) {
        return categories.stream().map(categoryRepository::findByIdOrThrowNotFound).collect(Collectors.toSet());
    }
}
