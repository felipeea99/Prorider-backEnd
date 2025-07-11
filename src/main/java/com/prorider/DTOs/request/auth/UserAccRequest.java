package com.prorider.DTOs.request.auth;

import com.prorider.entities.auth.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccRequest {
    @NotBlank(message = "El nombre esta vacio")
    private String name;
    @NotBlank(message = "El user name esta vacio")
    private String userName;
    @NotBlank(message = "El primer apellido esta vacio")
    private String userLastName1;
    @NotBlank(message = "El segundo apellido esta vacio")
    private String userLastName2;
    @NotBlank(message = "La contraseña esta vacia")
    private String password;
    @NotBlank(message = "El email esta vacio")
    @Email(message = "El email ingresado no tiene el formato correcto")
    private String email;
    @NotBlank( message = "El numero de celular esta vacio")
    @Pattern(regexp = "^[0-9]{10}$\"", message = "El numero de celular debe tener 10 digitos")
    private String phoneNumber;
    @NotNull(message = "Seleccione un Rol")
    @Enumerated(EnumType.STRING)
    private RoleType role;
}
