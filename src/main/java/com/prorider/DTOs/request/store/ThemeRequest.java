package com.prorider.DTOs.request.store;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThemeRequest {
    @NotNull(message = "el color primario debe estar definido")
    @Size(min = 7)
    private String primaryColor;
    @NotNull(message = "el color secundario debe estar definido")
    @Size(min = 7)
    private String secondaryColor;
    @NotNull(message = "El texto primario debe estar definido")
    @Size(min = 7)
    private String primaryTextColor;
    @NotNull(message = "El texto secundario  debe estar definido")
    @Size(min = 7)
    private String secondaryTextColor;
    @NotNull(message = "El fondo debe estar definido")
    @Size(min = 7)
    private String backgroundColor;
    @NotNull(message = "El 'color de seleccion' primario debe estar definido")
    @Size(min = 7)
    private String primaryHoverColor;
    @NotNull(message = "El 'color de seleccion' secundario debe estar definido")
    @Size(min = 7)
    private String secondaryHoverColor;
    @NotNull(message = "El 'texto de fondo' secundario debe estar definido")
    @Size(min = 7)
    private String backgroundTextColor;
    @NotNull(message = "El 'fondo de la tarjeta' secundario debe estar definido")
    @Size(min = 7)
    private String cardBackground;
    @NotNull(message = "El 'texto de la tarjeta' secundario debe estar definido")
    @Size(min = 7)
    private String cardText;
    private boolean isActive;
}
