package com.i2i.ibus.model;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
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
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id=?")
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String mailId;
    @NotNull
    private String gender;
    @NotNull
    private int age;
    @Column(columnDefinition = "bit default 0")
    @NotNull
    private boolean isDeleted;    
}
