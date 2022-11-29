package com.i2i.ibus.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "operator")
public class Operator {
    
    @Id
    @Column
    private int id;
    @Column
    private String travelsName;
    @Column
    private String ownerName;
    @Column
    private String phoneNo;
    @Column
    private String mailId;
    @Column
    private String panNo;
    @Column
    private String gstNo;
    @Column
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Address> addresses;
}
