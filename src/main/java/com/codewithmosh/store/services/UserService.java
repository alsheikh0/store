package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.changePasswordRequest;
import com.codewithmosh.store.dtos.registerUserRequest;
import com.codewithmosh.store.dtos.updateUserRequest;
import com.codewithmosh.store.entities.Role;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.exceptions.AccessDeniedException;
import com.codewithmosh.store.exceptions.UserNotFoundException;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Builder
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

    public List<UserDto> getAllUsers() {
        var users = userRepository.getAll();
        return userMapper.toDto(users);
    }

    public UserDto getUserById(Long id) {
        var user = userRepository.getUserBy(id).orElse(null);
        if (user == null) throw new UserNotFoundException("no user with that id");
        return userMapper.toDto(user);
    }

    public UserDto registerUser(registerUserRequest request) {
        var user = new User();
        logger.info("user created {}", user);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        logger.info("user populated {}", user);
        userRepository.save(user);
        return userMapper.toDto(user);

    }

    public UserDto updateUser(updateUserRequest request, Long id) {
        var user = userRepository.getUserBy(id).orElse(null);
        if (user == null) throw new UserNotFoundException("no user with that id");
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getName() != null) user.setName(request.getName());
        userRepository.save(user);
        return userMapper.toDto(user);

    }

    public void deleteUser(Long id) {
        var user = userRepository.getUserBy(id).orElse(null);
        if (user == null) throw new UserNotFoundException("no user with that id");
        userRepository.delete(user);
    }

    public ResponseEntity<?> changePassword(Long id, changePasswordRequest request) {
        var user = userRepository.getUserBy(id).orElse(null);
        if (user == null) throw new UserNotFoundException("no user with that id");
        if (!request.getOldPassword().equals(user.getPassword()))
            throw new AccessDeniedException("old passwords do not match");
        if (request.getNewPassword() != null) user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }


}
