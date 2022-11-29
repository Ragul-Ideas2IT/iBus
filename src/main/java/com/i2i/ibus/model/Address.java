package com.i2i.ibus.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    
    @Id
    @Column
    private int id;
    @Column
    private String landmark;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String district;
    @Column
    private String state;
    @Column
    private int pincode;
    @Column
    private boolean isActive;
    
}
