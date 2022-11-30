package com.i2i.ibus.model;

import java.util.List;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ananth.
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id=?")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String busName;
    private String busNumber;
    private int numberOfSeats;
    private String type;
    @ManyToOne
    @JoinColumn(name = "operator_id")
    private Operator operator;
    @OneToMany(cascade = { jakarta.persistence.CascadeType.PERSIST })
    @JoinColumn(name = "bus_id")
    private List<BusHistory> busHistories;
    @OneToMany(cascade = { jakarta.persistence.CascadeType.PERSIST })
    @JoinColumn(name = "bus_id")
    private List<PickupPoint> pickupPoints;
    @Column(columnDefinition = "bit default 0")
    private boolean isDeleted;
}
