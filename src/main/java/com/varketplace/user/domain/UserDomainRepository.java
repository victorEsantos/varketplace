package com.varketplace.user.domain;

import com.varketplace.infra.exception.ResourceNotFoundException;
import com.varketplace.user.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDomainRepository {
    void save(User product);

    @Deprecated
    User findById(UUID id);

    default User findByIdOrThrowNotFound(UUID id) {
        var product = this.findById(id);

        if (product == null)
            throw new ResourceNotFoundException("User not found");
        return product;
    }

    List<User> findAll();

    Optional<User> findByName(String name);
}
