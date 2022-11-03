package com.varketplace.category.service;

import com.varketplace.category.FindCategoryUseCase;
import com.varketplace.category.domain.CategoryDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Objects.isNull;

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

    @Override
    public Page<CategoryDto> handle(FindAllCategoryCommand cmd) {
        var categories = repository.findAll(cmd.getPageable());
        if(isNull(categories)) {
            return null;
        }


        return new PageImpl<>(categories.stream().map(CategoryDto::from).toList());
    }
}
