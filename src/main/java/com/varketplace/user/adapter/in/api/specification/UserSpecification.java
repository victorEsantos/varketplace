package com.varketplace.user.adapter.in.api.specification;

import com.varketplace.user.domain.model.User;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    @And({
        @Spec(path = "email", spec = Like.class),
        @Spec(path = "name", spec = Like.class)
    })
    public interface UserSpec extends Specification<User> {
    }

}
