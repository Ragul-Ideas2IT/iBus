package com.i2i.ibus.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
 * @created Nov 29 2022
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table
@SQLDelete(sql = "UPDATE booking SET is_deleted = true WHERE id=?")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double totalFare;
    private LocalDateTime dateTime;
    private String source;
    private String destination;
    private String pickUpPoint;
    private String dropPoint;
    private String status;
    private String paymentStatus;
    private LocalTime travelTime;
    private LocalDate travelDate;
    private boolean isDeleted;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private List<BookingDetail> bookingDetails;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "cancellation_id")
    private Cancellation cancellation;
    @OneToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

}
