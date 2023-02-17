package com.example.pullpointdev.notification;

import com.example.pullpointdev.notification.model.PlannedNotification;
import com.example.pullpointdev.notification.model.PlannedNotificationType;
import com.example.pullpointdev.notification.repository.PlannedNotificationRepository;
import com.example.pullpointdev.pullpoint.model.PullPoint;
import com.example.pullpointdev.pullpoint.service.PullPointService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@EnableScheduling
@Slf4j
@Service
@RequiredArgsConstructor
public class PlannedChecker {
    private final PlannedNotificationRepository plannedNotificationRepository;
    private final PullPointService pullPointService;
    private final NotificationService notificationService;

    @Scheduled(fixedRate=60000)
    @SneakyThrows
    @Transactional
    public void checkPlannedNots() {
        log.info("checking planned notifications");
        List<PlannedNotification> nots = plannedNotificationRepository.findAll();
        if(!nots.isEmpty()) {
            for (PlannedNotification not : nots) {
                if (not.getType() == PlannedNotificationType.PP_END){
                    pullPointService.closePP(not.getReceiver().getPhone());
                }
                notificationService.sendNotification(not.getReceiver(), not.getArtist(), not.getType());
                plannedNotificationRepository.delete(not);
            }
        }
    }
}
