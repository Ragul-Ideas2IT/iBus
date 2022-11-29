package com.i2i.ibus.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    
    private int id;
    private String landmark;
    private String street;
    private String city;
    private String district;
    private String state;
    private int pincode;
    
}
