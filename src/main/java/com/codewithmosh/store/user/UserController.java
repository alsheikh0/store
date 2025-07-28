package com.codewithmosh.store.user;

import com.codewithmosh.store.auth.AccessDeniedException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("all")
    public List<UserDto> getUsers(
            ) {

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(
            @PathVariable Long id
    ) {
        return userService.getUserById(id);
    }

    @PostMapping("register")
    public UserDto registerUser(
            @RequestBody registerUserRequest request

    ) {
        return userService.registerUser(request);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(
            @PathVariable Long id,
            @RequestBody updateUserRequest request
    ) {
        return userService.updateUser(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<?> changePassword(
            @PathVariable Long id,
            @RequestBody changePasswordRequest request
    ) {
        return userService.changePassword(id, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Void> handleUserNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateUser() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(Map.of("error", "user already exists with this email"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Void> handleAccessDenied() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    //just comments
}
