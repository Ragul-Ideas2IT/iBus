package com.i2i.ibus.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.i2i.ibus.model.Cancellation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
 * @created Nov 29 2022
 */
@Setter
@Getter
@NoArgsConstructor
public class BookingDto {
    
    @JsonProperty(access = Access.READ_ONLY)
    private int id; 
    @JsonProperty(access = Access.READ_ONLY)
    private double totalFare;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,50}")
    private String pickUpPoint;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,50}")
    private String dropPoint;
    @JsonProperty(access = Access.READ_ONLY)
    private String status;
    @JsonProperty(access = Access.READ_ONLY)
    private String paymentStatus;
    @FutureOrPresent
    private LocalTime travelTime;
    @NotBlank
    @FutureOrPresent
    private LocalDate travelDate;
    @NotEmpty
    @Valid
    private List<BookingDetailDto> bookingDetails;
    @JsonProperty(access = Access.READ_ONLY)    
    private LocalDateTime dateTime;
    @JsonProperty(access = Access.READ_ONLY) 
    private Cancellation cancellation;
}
