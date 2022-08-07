package com.varketplace.product.domain.model;

import com.varketplace.category.domain.model.Category;
import com.varketplace.infra.domain.DomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Product extends DomainEntity {

    private static final long serialVersionUID = -7129824927913652212L;

    @Id
    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "product_category",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        this.getCategories().add(category);
    }
}
