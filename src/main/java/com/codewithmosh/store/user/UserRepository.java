package com.codewithmosh.store.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u")
    List<User> getAll();

    @Query("select u from User u where u.id = :userId")
    Optional<User> getUserBy(Long userId);

    @Query("select u from User u where u.email = :email")
    Optional<User>findByEmail(String email);
}
