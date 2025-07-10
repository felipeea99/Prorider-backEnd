package com.prorider.DTOs.request.store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    @NotBlank(message = "El campo 'Pais' esta vacio")
    @Size(min = 4)
    private String country;
    @NotBlank(message = "El campo 'calle' esta vacio")
    private String street;
    @NotBlank(message = "El campo 'numero' esta vacio")
    private String number;
    @NotBlank(message = "El campo 'colonia' esta vacio")
    private String colony;
    @NotBlank(message = "El campo 'ciudad' esta vacio")
    private String town;
    @NotBlank(message = "El campo 'userId' esta vacio")
    private UUID userId;
}
