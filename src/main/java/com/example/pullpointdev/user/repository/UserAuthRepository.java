package com.example.pullpointdev.user.repository;

import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByOwner(User user);
}
