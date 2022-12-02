package com.i2i.ibus.dto;

import java.time.LocalDateTime;

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

    private double amount;
    private String status;
    private String modeOfPayment;
    private LocalDateTime time;

}