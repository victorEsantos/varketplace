package com.varketplace.product.domain;

import com.varketplace.infra.exception.ResourceNotFoundException;
import com.varketplace.product.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductDomainRepository {
    void save(Product product);

    Product findById(UUID id);

    default Product findByIdOrThrowNotFound(UUID id){
        var product = this.findById(id);

        if(product == null)
            throw new ResourceNotFoundException("Product not found");
        return product;
    }

    Page<Product> findAll(Pageable pageable);
}
