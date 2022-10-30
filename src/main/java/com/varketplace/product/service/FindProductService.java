package com.varketplace.product.service;

import com.varketplace.product.FindProductUseCase;
import com.varketplace.product.domain.ProductDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
