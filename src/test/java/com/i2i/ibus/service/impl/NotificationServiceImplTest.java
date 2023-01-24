package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.NotificationDto;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Notification;
import com.i2i.ibus.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class NotificationServiceImplTest {

    @Autowired
    private NotificationRepository notificationRepositoryDB;

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    private Notification notificationDB;

    private NotificationDto notificationDto;
    @BeforeEach
    void setUp() {
        int id = 1;
        notificationDB = notificationRepositoryDB.findById(id).get();
        notificationDto = Mapper.toNotificationDto(notificationDB);
    }

    @Test
    void addNotification() {
        when(notificationRepository.findById(notificationDB.getId())).thenReturn(Optional.of(notificationDB));
        NotificationDto notification = notificationService.addNotification(notificationDto);
        assertNotNull(notification);
        assertEquals(notification.getReferenceId(), notificationDB.getReferenceId());
    }
}