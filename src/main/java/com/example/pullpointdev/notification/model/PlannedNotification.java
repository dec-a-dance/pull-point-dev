package com.example.pullpointdev.notification.model;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.user.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PlannedNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User receiver;

    @Enumerated(value = EnumType.STRING)
    private PlannedNotificationType type;

    private Date time;

    @ManyToOne
    private Artist artist;
}