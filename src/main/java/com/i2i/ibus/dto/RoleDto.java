package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RoleDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    @Pattern(regexp = "(?i)^(user)|(operator)$", message = "Role should be user or operator")
    @NotBlank(message = "Role name is mandatory")
    private String name;
}
