package com.i2i.ibus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.service.NotificationService;

/**
 * 
 * @author Tamilmani
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