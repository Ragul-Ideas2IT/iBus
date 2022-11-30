package com.i2i.ibus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.service.NotificationService;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@RestController
public class NotificationController {

    private NotificationService notificationService;

    @Autowired
    private NotificationController(NotificationService notificationService) {
	this.notificationService = notificationService;
    }

}