package com.i2i.ibus.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
    @NotBlank(message = "Mail ID is mandatory")
    @Email(message = "Email ID should be in valid format")
    private String id;
    @Pattern(regexp = "(?i)^(user)|(operator)$", message = "Role should be user or operator")
    @NotBlank(message = "Role is mandatory")
    private String role;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
