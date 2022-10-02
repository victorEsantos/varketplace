package com.varketplace.user.domain;

import com.varketplace.infra.exception.ResourceNotFoundException;
import com.varketplace.user.adapter.in.api.specification.UserSpecification;
import com.varketplace.user.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDomainRepository {
    void save(User product);

    Optional<User> findById(UUID id);

    default User findByIdOrThrowNotFound(UUID id) {
        var product = this.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return product;
    }

    List<User> findAll();

    Optional<User> findByName(String name);

    Page<User> findAll(UserSpecification.UserSpec spec, Pageable pageable);

    void delete(User user);
}
