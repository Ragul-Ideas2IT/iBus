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
@Table(name = "user")
public class User {

    @Id
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String phoneNumber;
    @Column
    private String mailId;
    @Column
    private String gender;
    @Column
    private int age;
    @Column
    private boolean isActive;    
}
