package com.varketplace.product.service;

import com.varketplace.category.domain.CategoryDomainRepository;
import com.varketplace.product.InsertCaregoryInProductUseCase;
import com.varketplace.product.domain.ProductDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class InsertCategoryInProductService implements InsertCaregoryInProductUseCase {

    private final ProductDomainRepository repository;
    private final CategoryDomainRepository categoryRepository;

    @Override
    public void handle(InsertCaregoryOnProductCommand cmd, UUID productId) {

        var product = repository.findByIdOrThrowNotFound(productId);

        var category = categoryRepository.findByIdOrThrowNotFound(UUID.fromString(cmd.getCategoryId()));

        product.addCategory(category);

        repository.save(product);

    }
}
