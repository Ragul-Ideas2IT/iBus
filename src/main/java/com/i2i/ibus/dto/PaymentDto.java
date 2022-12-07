package com.i2i.ibus.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    @Pattern(regexp = "(?i)^(upi)|(debit)|(credit)$", message = "only credit, debit, upi payments are allowed.")
    private String modeOfPayment;
    @Min(0)
    @Max(999)
    private int cvvNumber;
    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]{15}$")
    private String cardNumber;
    @NotBlank
    @Pattern(regexp = "^([a-zA-Z][ ]?){2,50}$")
    private String cardHolderName;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime time;

}