package com.i2i.ibus.model;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 28 2022
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@SQLDelete(sql = "UPDATE operator SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String travelsName;
    private String ownerName;
    private String phoneNumber;
    private String mailId;
    private String panNumber;
    private String gstNumber;
    private boolean isDeleted;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "operator_id")
    private List<Address> addresses;
}
