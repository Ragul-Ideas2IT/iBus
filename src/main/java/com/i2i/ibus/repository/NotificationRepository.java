/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.repository;

import com.i2i.ibus.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tamilmani K
 * @version 1.0
 * @since Nov 29 2022
 *
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
