package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @created Nov 29 2022
 *
 */
@Getter
@Setter
public class SeatDto {

    private int id;
    @NotBlank
    @Pattern(regexp = "^((?i)(sleeper)|(semisleeper)|(seater))$")
    private String seatType;
    @NotBlank
    @Pattern(regexp = "^([L]|[R])([L]|[U])([S]|[B])([0-9]?[1-9])$")
    private String seatNumber;
    @NotBlank
    @Pattern(regexp = "^((?i)(male)|(female)|(others))$")
    private String gender;
    @JsonProperty(access = Access.READ_ONLY)
    private BusDto bus;
    @Min(value = 0)
    private double fare;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean isOccupied;
}