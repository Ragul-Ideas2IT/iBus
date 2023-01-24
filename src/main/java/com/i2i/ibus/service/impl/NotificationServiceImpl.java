package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.NotificationDto;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Notification;
import com.i2i.ibus.repository.NotificationRepository;
import com.i2i.ibus.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The notified messages are passed to the {@link NotificationRepository} to store
 * of the notification details in the database and to get the notification details
 * and delete operation are processed.
 *
 * @author Tamilmani K
 * @version 1.0
 * @since Nov 29 2022
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;


    /**
     * Create a new notification repository to initialing the specified
     * targets to connect the database.
     *
     * @param notificationRepository To access the notification details.
     */
    @Autowired
    private NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotificationDto addNotification(NotificationDto notificationDto) {
        Notification notification = notificationRepository.save(Mapper.toNotification(notificationDto));
        return Mapper.toNotificationDto(notification);
    }

}
