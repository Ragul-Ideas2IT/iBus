package com.i2i.ibus.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class PickupPointDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,}")
    private String stopName;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,}")
    private String landMark;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,}")
    private String city;
    @NotNull
    private LocalTime arrivingTime;
    @NotNull
    private LocalTime departureTime;
    @JsonProperty(access = Access.READ_ONLY)
    private BusDto bus;

}
