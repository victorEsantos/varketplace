package com.varketplace.category.service;

import com.varketplace.category.FindCategoryUseCase;
import com.varketplace.category.domain.CategoryDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FindCategoryService implements FindCategoryUseCase {

    private final CategoryDomainRepository repository;

    @Override
    public CategoryDto handle(FindCategoryByIdCommand cmd) {
        var category = repository.findById(cmd.getId());

        return CategoryDto.from(category);
    }
}
