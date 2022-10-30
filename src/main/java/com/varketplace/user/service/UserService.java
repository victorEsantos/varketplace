package com.varketplace.user.service;

import com.varketplace.user.adapter.in.api.dto.UserDto;
import com.varketplace.user.adapter.in.api.specification.UserSpecification;
import com.varketplace.user.domain.UserDomainRepository;
import com.varketplace.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserDomainRepository repository;

//    public Page<User> getAll(UserSpecification.UserSpec spec, Pageable pageable){
//        return repository.findAll(spec, pageable);
//    }

    public Optional<User> findById(UUID userId) {
        return repository.findById(userId);
    }

    public User findByIdOrThrowNotFound(UUID userId) {
        return repository.findByIdOrThrowNotFound(userId);
    }

    public void deleteUser(User user) {
        repository.delete(user);
        log.debug("DELETE deleteUser userId deleted {} ", user.getId());
        log.info("User deleted successfully: {} ", user.getName());
    }

    public User updateUser(UUID userId, UserDto userDto) {
        log.debug("PUT updateUser userDto received {} ", userDto.toString());

        User usr = this.findByIdOrThrowNotFound(userId);

        usr.setName(userDto.getFullName());
        usr.setEmail(userDto.getEmail());

        repository.save(usr);
        log.debug("UPDATE updateUser userId updated {} ", usr.getId());
        log.info("User updated successfully: {} ", usr.getName());

        return usr;
    }

    public void updatePassword(User userModel) {
        repository.save(userModel);
        log.debug("UPDATE updatePassword updated {} ", userModel.getId());
        log.info("User password updated successfully: {} ", userModel.getName());
    }
}
