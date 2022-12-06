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
    @Min(value = 1)
    @Max(value = 999)
    private int CVVNumer;
    @NotBlank
    @Pattern(regexp = "^((4\\d{3})|(5[1-5]\\d{2})|(34\\d{1})|(37\\d{1}))-?\\s?\\d{4}-?\\s?\\d{4}-?\\s?\\d{4}|3[4,7][\\d\\s-]{15}$")
    private String cardNumber;
    @NotBlank
    @Pattern(regexp = "^([a-zA-Z][ ]?){2,50}$")
    private String carHolderName;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime time;

}