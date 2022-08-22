package com.example.pullpointdev.repository;

import com.example.pullpointdev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhone(String phone);

    User findById(long id);
}
