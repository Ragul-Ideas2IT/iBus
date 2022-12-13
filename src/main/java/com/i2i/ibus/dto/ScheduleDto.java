package com.i2i.ibus.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class ScheduleDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @NotNull
    @FutureOrPresent
    private LocalDate departureDate;
    @NotNull
    private LocalTime departureTime;
    @NotNull
    @FutureOrPresent
    private LocalDate arrivingDate;
    @NotNull
    private LocalTime arrivingTime;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,}")
    private String source;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,}")
    private String destination;
    @JsonProperty(access = Access.READ_ONLY)
    private BusDto bus;
    private LocalTime actualDepartureTime;
    private LocalTime actualArrivingTime;
    @JsonProperty(access = Access.READ_ONLY)
    private String status;

}
