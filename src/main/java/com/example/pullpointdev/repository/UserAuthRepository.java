package com.example.pullpointdev.repository;

import com.example.pullpointdev.entity.User;
import com.example.pullpointdev.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByOwner(User user);
}
