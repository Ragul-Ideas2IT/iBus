package com.i2i.ibus.dto;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Ragul
 * @version 1.0
 * 
 * @since 28 Nov 2022
 *
 */
@Getter
@Setter
public class UserDto {

    private int id;
    private String name;
    private String phoneNumber;
    private String mailId;
    private String gender;
    private int age;
}
