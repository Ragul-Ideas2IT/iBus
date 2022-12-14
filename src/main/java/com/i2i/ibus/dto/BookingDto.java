package com.i2i.ibus.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.i2i.ibus.constants.Constants;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
 * @since Nov 29 2022
 */
@Setter
@Getter
@NoArgsConstructor
public class BookingDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    private int busId;
    @NotBlank
    private int userId;
    @JsonProperty(access = Access.READ_ONLY)
    private double totalFare;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN)
    private String pickUpPoint;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN)
    private String dropPoint;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN)
    private String source;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN)
    private String destination;
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
    private CancellationDto cancellation;
}
