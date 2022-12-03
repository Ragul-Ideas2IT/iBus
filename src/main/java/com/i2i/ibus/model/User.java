package com.i2i.ibus.model;

import org.hibernate.annotations.SQLDelete;

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
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id=?")
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;
    private String mailId;
    private String gender;
    private int age;
    private boolean isDeleted; 
}
