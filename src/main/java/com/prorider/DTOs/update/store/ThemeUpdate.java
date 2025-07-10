package com.prorider.DTOs.update.store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThemeUpdate {

    private Integer themeId;

    @NotBlank(message = "El color primario no debe estar vacío")
    @Size(min = 7, message = "El color primario debe tener al menos 7 caracteres")
    private String primaryColor;

    @NotBlank(message = "El color secundario no debe estar vacío")
    @Size(min = 7, message = "El color secundario debe tener al menos 7 caracteres")
    private String secondaryColor;

    @NotBlank(message = "El color de texto primario no debe estar vacío")
    @Size(min = 7, message = "El color de texto primario debe tener al menos 7 caracteres")
    private String primaryTextColor;

    @NotBlank(message = "El color de texto secundario no debe estar vacío")
    @Size(min = 7, message = "El color de texto secundario debe tener al menos 7 caracteres")
    private String secondaryTextColor;

    @NotBlank(message = "El color de fondo no debe estar vacío")
    @Size(min = 7, message = "El color de fondo debe tener al menos 7 caracteres")
    private String backgroundColor;

    @NotBlank(message = "El color hover primario no debe estar vacío")
    @Size(min = 7, message = "El color hover primario debe tener al menos 7 caracteres")
    private String primaryHoverColor;

    @NotBlank(message = "El color hover secundario no debe estar vacío")
    @Size(min = 7, message = "El color hover secundario debe tener al menos 7 caracteres")
    private String secondaryHoverColor;

    @NotBlank(message = "El color del texto de fondo no debe estar vacío")
    @Size(min = 7, message = "El color del texto de fondo debe tener al menos 7 caracteres")
    private String backgroundTextColor;

    @NotBlank(message = "El color de fondo de la tarjeta no debe estar vacío")
    @Size(min = 7, message = "El color de fondo de la tarjeta debe tener al menos 7 caracteres")
    private String cardBackground;

    @NotBlank(message = "El color de texto de la tarjeta no debe estar vacío")
    @Size(min = 7, message = "El color de texto de la tarjeta debe tener al menos 7 caracteres")
    private String cardText;

    private boolean isActive;
}
