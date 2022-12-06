package com.i2i.ibus.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
 * @created Nov 29 2022
 */
@Getter
@Setter
public class CancellationDto {
    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dateAndTime;
    @JsonProperty(access = Access.READ_ONLY)
    private double refundAmount;
    @JsonProperty(access = Access.READ_ONLY)
    private String status;
}
