package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.NotificationDto;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Notification;
import com.i2i.ibus.repository.NotificationRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
@Log4j2
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NotificationServiceImplTest {

    @Mock
    NotificationRepository notificationRepository;

    @Autowired
    NotificationRepository notificationRepositoryDB;
    @InjectMocks
    NotificationServiceImpl notificationService;

    Notification notification;

    NotificationDto notificationDto;

    @BeforeAll
    public void init() {
        int id = 1;
        notification = notificationRepositoryDB.findById(id).get();
        notificationDto = Mapper.toNotificationDto(notification);
    }

    @Test
    public void addNotification() {
        when(notificationRepository.save(ArgumentMatchers.any(Notification.class))).thenReturn(notification);
        NotificationDto newNotificationDto = notificationService.addNotification(notificationDto);
        log.info(notificationDto.getReferenceId());
        log.info(newNotificationDto.getReferenceId());
        org.assertj.core.api.Assertions.assertThat(newNotificationDto).usingRecursiveComparison().isEqualTo(notificationDto);
        Assertions.assertEquals(notificationDto.getReferenceId(), newNotificationDto.getReferenceId());
    }
}