package com.varketplace.user.service;

import com.varketplace.user.GetAllUsersUseCase;
import com.varketplace.user.domain.UserDomainRepository;
import com.varketplace.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GetAllUsersService implements GetAllUsersUseCase {

    private final UserDomainRepository repository;

    @Override
    public Page<User> handle(GetAllUsersCommand cmd) {
//        return repository.findAll(cmd.getSpec(), cmd.getPageable());
        return null;
    }
}
