package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 28 2022
 *
 */
@Getter
@Setter
public class UserDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @Pattern(regexp = "^([a-zA-Z][ ]?){2,}$", message = "User name should be in valid format")
    @NotBlank(message = "User name is mandatory")
    private String name;
    @Pattern(regexp = "^(\\+91|0)?[6789][0-9]{9}$", message = "Phone no should be 10 digits or with \"+91\" or \"0\"")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;
    @NotBlank(message = "Mail ID is mandatory")
    @Email(message = "Email ID should be in valid format")
    private String mailId;
    @Pattern(regexp = "(?i)^(male)|(female)|(others)$", message = "Gender should be male, female or others")
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    @Min(18)
    @Max(120)
    private int age;
}