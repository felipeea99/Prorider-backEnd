package com.prorider.DTOs.response.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginDTO {
    @Email(message = "Correo invalido")
    @NotBlank(message = "El correo no puede estar vacío")
    private String userName;
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;
}
