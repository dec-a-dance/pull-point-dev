package com.example.pullpointdev.notification;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.notification.model.PlannedNotificationType;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.repository.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final UserRepository userRepository;

    @SneakyThrows
    public void sendNotification(User user, Artist artist, PlannedNotificationType type){
        String registrationToken = "";
        if (user != null) {
            registrationToken = user.getNotificationsToken();
        }
        String artistName = artist.getName();
        Message message;
        String response;
        switch (type){
            case PP_END_WARN:
                message = Message.builder().setNotification(Notification.builder().setTitle("Выступление скоро закончится").setBody(artistName + ", ваше выступление завершится через 10 минут").build()).setToken(registrationToken).build();
                response = FirebaseMessaging.getInstance().send(message);
                log.info("Successfully sent warning message: " + response);
                break;
            case PP_END:
                message = Message.builder().setNotification(Notification.builder().setTitle("Выступление закончено").setBody(artistName + ", ваше выступление было автоматически завершено!").build()).setToken(registrationToken).build();
                response = FirebaseMessaging.getInstance().send(message);
                log.info("Successfully sent end message: " + response);
                break;
            case SUBSCRIBE_CREATE:
                message = Message.builder().setNotification(Notification.builder().setTitle(artistName + "создал выступление!").setBody(artistName + " создал выступление! Заглянешь на огонек?").build()).setTopic(artistName).build();
                response = FirebaseMessaging.getInstance().send(message);
                log.info("Successfully sent create message: " + response);
                break;
            case SUBSCRIBE_START:
                message = Message.builder().setNotification(Notification.builder().setTitle(artistName + "начинает выступление!").setBody(artistName + " начинает выступать! Поторопись, а то всё пропустишь.").build()).setTopic(artistName).build();
                response = FirebaseMessaging.getInstance().send(message);
                log.info("Successfully sent start message: " + response);
                break;
        }
    }
}