package com.example.pullpointdev.notification;

import com.example.pullpointdev.user.repository.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final UserRepository userRepository;

    public void sendEndPPWarningNotification(String phone) throws FirebaseMessagingException {
        String registrationToken = userRepository.findByPhone(phone).orElseThrow(() -> new NullPointerException("no such user")).getNotificationsToken();
        Message message = Message.builder().putData("text", "your pull point will end in 10 minutes").setToken(registrationToken).build();
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);
    }

    public void sendEndPPNotification(String phone) throws FirebaseMessagingException {
        String registrationToken = userRepository.findByPhone(phone).orElseThrow(() -> new NullPointerException("no such user")).getNotificationsToken();
        Message message = Message.builder().putData("text", "your pull point ended").setToken(registrationToken).build();
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);
    }
}
