package com.varketplace.product.adapter.out.db;

import com.varketplace.product.domain.ProductDomainRepository;
import com.varketplace.product.domain.model.Product;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface ProductJpaRepository extends ProductDomainRepository, Repository<Product, UUID> {
}
