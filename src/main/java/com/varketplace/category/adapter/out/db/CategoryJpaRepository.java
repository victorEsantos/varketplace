package com.varketplace.category.adapter.out.db;

import com.varketplace.category.domain.CategoryDomainRepository;
import com.varketplace.category.domain.model.Category;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface CategoryJpaRepository extends CategoryDomainRepository, Repository<Category, UUID> {
}
