package com.example.pizzashop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCredentials {

    @NotNull(message = "Email should not be empty")
    @NotEmpty(message = "Email should not be empty")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
    private String email;

    @NotNull(message = "Password should not be empty")
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 1, max = 64, message = "Password length must be between 1 and 64")
    private String password;

    private String name;

    private String phoneNumber;
}
