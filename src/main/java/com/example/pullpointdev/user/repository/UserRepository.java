package com.example.pullpointdev.user.repository;

import com.example.pullpointdev.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhone(String phone);

    User findById(long id);
}
