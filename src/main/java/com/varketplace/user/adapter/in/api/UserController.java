//package com.varketplace.user.adapter.in.api;
//
//import com.fasterxml.jackson.annotation.JsonView;
//import com.varketplace.config.security.AuthenticationCurrentUserService;
//import com.varketplace.config.security.UserDetailsImpl;
//import com.varketplace.user.GetAllUsersUseCase;
//import com.varketplace.user.adapter.in.api.dto.UserDto;
//import com.varketplace.user.adapter.in.api.specification.UserSpecification;
//import com.varketplace.user.domain.model.User;
//import com.varketplace.user.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Optional;
//import java.util.UUID;
//
//@Log4j2
//@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/users")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final GetAllUsersUseCase getAllUsersUseCase;
//
//    private final AuthenticationCurrentUserService authenticationCurrentUserService;
//
//    private final UserService userService;
//
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    @GetMapping
//    public ResponseEntity<Page<User>> getAllUsers(UserSpecification.UserSpec spec,
//                                                  @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
//                                                  Authentication authentication) {
//        UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        log.info("Authentication {}", userDetails.getUsername());
//
//
////        Page<User> userModelPage = userService.getAll(spec, pageable);
//        Page<User> userModelPage = null;
//
//        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
//    }
//
//    @PreAuthorize("hasAnyRole('STUDENT')")
//    @GetMapping("/{userId}")
//    public ResponseEntity<Object> getById(@PathVariable(value = "userId") UUID userId) {
//        UUID currentUserId = authenticationCurrentUserService.getCurrentUser().getId();
//        if (currentUserId.equals(userId)) {
//            Optional<User> userModelOptional = userService.findById(userId);
//            if (!userModelOptional.isPresent()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//            } else {
//                return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
//            }
//        } else {
//            throw new AccessDeniedException("Forbidden");
//        }
//    }
//
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<Object> delete(@PathVariable(value = "userId") UUID userId) {
//        log.debug("DELETE deleteUser userId received {} ", userId);
//        User usr = userService.findByIdOrThrowNotFound(userId);
//        userService.deleteUser(usr);
//
//        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
//    }
//
//    @PutMapping("/{userId}")
//    public ResponseEntity<Object> update(@PathVariable(value = "userId") UUID userId,
//                                         @RequestBody @Validated(UserDto.UserView.UserPut.class)
//                                         @JsonView(UserDto.UserView.UserPut.class) UserDto userDto) {
//
//        var usr = userService.updateUser(userId, userDto);
//
//        return ResponseEntity.status(HttpStatus.OK).body(usr);
//    }
//
//    @PutMapping("/{userId}/password")
//    public ResponseEntity<Object> updatePassword(@PathVariable(value = "userId") UUID userId,
//                                                 @RequestBody @Validated(UserDto.UserView.PasswordPut.class)
//                                                 @JsonView(UserDto.UserView.PasswordPut.class) UserDto userDto) {
//        log.debug("PUT updatePassword userDto received {} ", userDto.toString());
//        Optional<User> userModelOptional = userService.findById(userId);
//        if (!userModelOptional.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//        }
//        if (!userModelOptional.get().getPassword().equals(userDto.getOldPassword())) {
//            log.warn("Mismatched old password userId {} ", userId);
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password!");
//        } else {
//            var userModel = userModelOptional.get();
//            userModel.setPassword(userDto.getPassword());
//            userService.updatePassword(userModel);
//            return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully.");
//        }
//    }
//
//
//}
