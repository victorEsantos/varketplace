package com.varketplace.category.service;

import com.varketplace.category.CreateCategoryUseCase;
import com.varketplace.category.domain.CategoryDomainRepository;
import com.varketplace.category.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateCategoryService implements CreateCategoryUseCase {

    private final CategoryDomainRepository repository;

    @Override
    public UUID handle(CreateCategoryCommand cmd) {
        var category = Category.builder()
            .id(UUID.randomUUID())
            .name(cmd.getName())
            .description(cmd.getDescription())
            .build();

        repository.save(category);

        return category.getId();
    }
}
