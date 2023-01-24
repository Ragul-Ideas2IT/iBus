/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.service;

import com.i2i.ibus.dto.NotificationDto;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
public interface NotificationService {

    /**
     * Notification details are saved to the database.
     *
     * @param notificationDto To save the notification details.
     * @return NotificationDto The saved notification details.
     */
    NotificationDto addNotification(NotificationDto notificationDto);

}
