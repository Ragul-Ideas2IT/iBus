package com.i2i.ibus.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Where(clause = "is_deleted = false")
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String landmark;
    private String street;
    private String city;
    private String district;
    private String state;
    private int pincode;
    private boolean isDeleted;
    
}
