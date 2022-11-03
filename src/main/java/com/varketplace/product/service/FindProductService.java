package com.varketplace.product.service;

import com.varketplace.product.FindProductUseCase;
import com.varketplace.product.domain.ProductDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Objects.isNull;

@Service
@Transactional
@RequiredArgsConstructor
public class FindProductService implements FindProductUseCase {

    private final ProductDomainRepository repository;

    @Override
    public ProductDto handle(FindProductByIdCommand cmd) {
        var product = repository.findById(cmd.getId());

        return ProductDto.from(product);
    }

    @Override
    public Page<ProductDto> handle(FindAllProductCommand cmd) {
        var products = repository.findAll(cmd.getPageable());

        if(isNull(products)) {
            return null;
        }
        return new PageImpl<>(products.stream().map(ProductDto::from).toList());
    }
}
