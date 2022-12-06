package com.i2i.ibus.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@Getter
@Setter
public class PaymentDto {

    @Min(value = 0)
    private double amount;
    @JsonProperty(access = Access.READ_ONLY)
    private String status;
    @Pattern(regexp = "(?i)^(upi)|(debit)|(credit)$", message = "only credit, debit, upi payments are allowed.")
    private String modeOfPayment;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime time;

}