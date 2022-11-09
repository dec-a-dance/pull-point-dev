package com.example.pullpointdev.admin.repository;

import com.example.pullpointdev.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<User, Long>, ExtendedAdminRepository {
}
