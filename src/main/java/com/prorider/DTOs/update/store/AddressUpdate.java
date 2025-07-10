package com.prorider.DTOs.update.store;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AddressUpdate {
    @NotNull(message = "El campo 'addressId' esta vacío ")
    private int addressId;
    @NotBlank(message = "El campo 'País' es obligatorio")
    @Size(min = 4, message = "El campo 'País' debe tener al menos 4 caracteres")
    private String country;
    @NotBlank(message = "El campo 'Calle' es obligatorio")
    private String street;
    @NotBlank(message = "El campo 'Número' es obligatorio")
    private String number;
    @NotBlank(message = "El campo 'Colonia' es obligatorio")
    private String colony;
    @NotBlank(message = "El campo 'Ciudad' es obligatorio")
    private String town;
    @NotBlank(message = "El campo 'userId' es obligatorio")
    private UUID userId;
}
