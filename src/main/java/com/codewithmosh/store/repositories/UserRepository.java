package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u")
    List<User> getAll();

    @Query("select u from User u where u.id = :userId")
    Optional<User> getUserBy(Long userId);
}
