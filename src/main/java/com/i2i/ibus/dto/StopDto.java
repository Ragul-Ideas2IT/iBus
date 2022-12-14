package com.i2i.ibus.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.i2i.ibus.constants.Constants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Used to get the bus stop details from the operators.
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @created Nov 29 2022
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class StopDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN)
    private String stopName;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN)
    private String landMark;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN)
    private String city;
    @NotNull
    private LocalTime arrivingTime;
    @NotNull
    private LocalTime departureTime;
    @JsonProperty(access = Access.WRITE_ONLY)
    private int busId;
    @JsonProperty(access = Access.READ_ONLY)
    private BusDto bus;

}
