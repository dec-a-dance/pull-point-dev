package com.example.pullpointdev.notification.repository;

import com.example.pullpointdev.notification.model.PlannedNotification;
import com.example.pullpointdev.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannedNotificationRepository extends JpaRepository<PlannedNotification, Long> {
    void deleteByReceiver(User receiver);
}
