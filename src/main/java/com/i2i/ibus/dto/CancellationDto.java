package com.i2i.ibus.dto;

import java.time.LocalDateTime;

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

    private int id;
    private LocalDateTime dateAndTime;
    private double refundAmount;
    private boolean isCancelled;
}
