package com.varketplace.category.domain.model;

import com.varketplace.infra.domain.DomainEntity;
import com.varketplace.product.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends DomainEntity {

    private static final long serialVersionUID = -7129824927913652212L;

    @Id
    private UUID id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

}
