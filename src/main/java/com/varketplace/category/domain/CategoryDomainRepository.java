package com.varketplace.category.domain;

import com.varketplace.category.domain.model.Category;
import com.varketplace.infra.exception.ResourceNotFoundException;
import com.varketplace.product.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface CategoryDomainRepository {
    void save(Category product);

    @Deprecated
    Category findById(UUID id);

    default Category findByIdOrThrowNotFound(UUID id) {
        var category = this.findById(id);

        if (category == null)
            throw new ResourceNotFoundException("Category not found");
        return category;
    }

    List<Category> findAll();
}
