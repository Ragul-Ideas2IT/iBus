package com.i2i.ibus.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Ragul
 *
 */
@Getter
@Setter
public class OperatorDto {
    
    private int id;
    private String travelsName;
    private String ownerName;
    private String phoneNumber;
    private String mailId;
    private String panNumber;
    private String gstNumber;
    private List<AddressDto> addresses;
}
