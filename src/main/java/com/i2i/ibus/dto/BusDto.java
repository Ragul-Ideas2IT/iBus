package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.i2i.ibus.constants.Constants;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * Used to get the bus details from the operators.
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
    @Pattern(regexp = Constants.BUS_NUMBER_PATTERN)
    private String busNumber;
    @Min(value = 1)
    @Max(value = 40)
    private int numberOfSeats;
    @NotBlank
    @Pattern(regexp = Constants.BUS_TYPE_PATTERN)
    private String type;
    @JsonProperty(access = Access.WRITE_ONLY)
    private int operatorId;
    @JsonProperty(access = Access.READ_ONLY)
    private OperatorDto operator;
}
