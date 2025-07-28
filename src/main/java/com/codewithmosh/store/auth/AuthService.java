package com.codewithmosh.store.auth;

import com.codewithmosh.store.user.User;
import com.codewithmosh.store.user.UserNotFoundException;
import com.codewithmosh.store.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    public User getUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotFoundException("User details not found");
        }
        Long userId = (Long) authentication.getPrincipal();

        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User details not found")
        );
    }
}


