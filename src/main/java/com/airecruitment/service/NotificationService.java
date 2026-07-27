package com.airecruitment.service;

import com.airecruitment.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification createNotification(Notification notification);

    List<Notification> getNotificationsByCandidate(Long candidateId);

    Notification markAsRead(Long notificationId);

}