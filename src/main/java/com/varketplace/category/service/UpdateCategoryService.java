package com.varketplace.category.service;

import com.varketplace.category.UpdateCategoryUseCase;
import com.varketplace.category.domain.CategoryDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateCategoryService implements UpdateCategoryUseCase {

    private final CategoryDomainRepository repository;

    @Override
    public void handle(UpdateCategoryCommand cmd) {
        var category = repository.findById(cmd.getId());

        category.update(cmd.getName(), cmd.getDescription());

        repository.save(category);

    }
}
