package com.i2i.ibus.dto;

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
public class NotificationDto {

    private String message;
    private String reference_id;
    private boolean reference_type;

}