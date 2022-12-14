package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AccountDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    @NotBlank(message = "Mail ID is mandatory")
    @Email(message = "Email ID should be in valid format")
    private String mailId;
    @Valid
    private RoleDto role;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
