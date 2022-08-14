package com.varketplace.user.adapter.out.db;

import com.varketplace.user.domain.UserDomainRepository;
import com.varketplace.user.domain.model.User;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface UserJpaRepository extends UserDomainRepository, Repository<User, UUID> {
}
