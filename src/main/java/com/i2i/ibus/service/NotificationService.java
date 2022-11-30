package com.i2i.ibus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.repository.NotificationRepository;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService(NotificationRepository notificationRepository) {
	this.notificationRepository = notificationRepository;
    }

}