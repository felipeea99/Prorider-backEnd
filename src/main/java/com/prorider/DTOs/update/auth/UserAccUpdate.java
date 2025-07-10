package com.prorider.DTOs.update.auth;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccUpdate {
    private UUID userId;
    @NotBlank(message = "El nombre esta vacio")
    private String name;
    @NotBlank(message = "El primer apellido esta vacio")
    private String userLastName1;
    @NotBlank(message = "El segundo apellido esta vacio")
    private String userLastName2;
    @NotBlank( message = "El numero de celular esta vacio")
    @Pattern(regexp = "^[0-9]{10}$\"", message = "El numero de celular debe tener 10 digitos")
    private String phoneNumber;
}
