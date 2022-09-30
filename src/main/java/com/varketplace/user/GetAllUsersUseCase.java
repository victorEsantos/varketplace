package com.varketplace.user;

import com.varketplace.user.adapter.in.api.specification.UserSpecification;
import com.varketplace.user.domain.model.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllUsersUseCase {

    Page<User> handle(GetAllUsersCommand cmd);

    @Value
    @AllArgsConstructor(staticName = "of")
    @ApiModel(description = "Get all users")
    class GetAllUsersCommand {
        UserSpecification.UserSpec spec;
        Pageable pageable;
    }
}
