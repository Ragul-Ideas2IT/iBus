package com.i2i.ibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Notification;

/**
 * 
 * @author Tamilmani
 *
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}