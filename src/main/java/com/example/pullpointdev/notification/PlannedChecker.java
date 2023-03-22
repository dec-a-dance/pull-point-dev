package com.example.pullpointdev.notification;

import com.example.pullpointdev.notification.model.PlannedNotification;
import com.example.pullpointdev.notification.model.PlannedNotificationType;
import com.example.pullpointdev.notification.repository.PlannedNotificationRepository;
import com.example.pullpointdev.pullpoint.service.PullPointServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@EnableScheduling
@Slf4j
@Service
@RequiredArgsConstructor
public class PlannedChecker {
    private final PlannedNotificationRepository plannedNotificationRepository;
    private final PullPointServiceImpl pullPointServiceImpl;
    private final NotificationService notificationService;

    @Scheduled(fixedRate=60000)
    @SneakyThrows
    @Transactional
    public void checkPlannedNots() {
        Date now = new Date();
        List<PlannedNotification> nots = plannedNotificationRepository.findAll();
        if(!nots.isEmpty()) {
            for (PlannedNotification not : nots) {
                log.info(not.getTime().toString());
                log.info(now.toString());
                if(not.getTime().getTime() < now.getTime()) {
                    if (not.getType() == PlannedNotificationType.PP_END) {
                        pullPointServiceImpl.closePP(not.getReceiver().getPhone());
                    }
                    notificationService.sendNotification(not.getReceiver(), not.getArtist(), not.getType());
                    plannedNotificationRepository.delete(not);
                }
            }
        }
    }
}
