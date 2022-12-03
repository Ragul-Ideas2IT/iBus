package com.i2i.ibus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
