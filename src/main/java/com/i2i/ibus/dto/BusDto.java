package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
public class BusDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @NotBlank
    @Pattern(regexp = "(^[A-Z]{2}(([0][1-9])|([1-9][0-9]))([A-Z]{1})?[A-Z]{1}[0-9]{3}[1-9]{1}$)")
    private String busNumber;
    @Min(value = 1)
    @Max(value = 40)
    private int numberOfSeats;
    @NotBlank
    @Pattern(regexp = "^((?i)(NONAC)|(AC))$")
    private String type;
    private int operatorId;
    @JsonProperty(access = Access.READ_ONLY)
    private OperatorDto operator;
}
