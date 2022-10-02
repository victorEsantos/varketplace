package com.varketplace.category.domain;

import com.varketplace.category.domain.model.Category;
import com.varketplace.infra.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface CategoryDomainRepository {
    void save(Category product);

    Category findById(UUID id);

    default Category findByIdOrThrowNotFound(UUID id) {
        var category = this.findById(id);

        if (category == null)
            throw new ResourceNotFoundException("Category not found");
        return category;
    }

    List<Category> findAll();
}
