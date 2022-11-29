package com.i2i.ibus.model;

import java.util.List;

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
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Operator {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String travelsName;
    @NotNull
    private String ownerName;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String mailId;
    @NotNull
    private String panNumber;
    @NotNull
    private String gstNumber;
    @NotNull
    private boolean isDeleted;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "operator")
    private List<Address> addresses;
}
