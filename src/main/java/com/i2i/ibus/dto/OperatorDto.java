package com.i2i.ibus.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperatorDto {
    
    private int id;
    private String travelsName;
    private String ownerName;
    private String phoneNo;
    private String mailId;
    private String panNo;
    private String gstNo;
    private List<AddressDto> addresses;
}
