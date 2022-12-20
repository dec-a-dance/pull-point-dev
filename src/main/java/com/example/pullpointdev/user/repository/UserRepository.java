package com.example.pullpointdev.user.repository;

import com.example.pullpointdev.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);

    User findById(long id);

    Optional<User> findByUsername(String username);
}
