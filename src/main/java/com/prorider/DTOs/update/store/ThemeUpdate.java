package com.prorider.DTOs.update.store;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

public class ThemeUpdate {

    private Integer themeId;
    @Size(min = 7)
    private String primaryColor;
    @Size(min = 7)
    private String secondaryColor;
    @Size(min = 7)
    private String primaryTextColor;
    @Size(min = 7)
    private String secondaryTextColor;
    @Size(min = 7)
    private String backgroundColor;
    @Size(min = 7)
    private String primaryHoverColor;
    @Size(min = 7)
    private String secondaryHoverColor;
    @Size(min = 7)
    private String backgroundTextColor;
    @Size(min = 7)
    private String cardBackground;
    @Size(min = 7)
    private String cardText;
    @Size(min = 7)
    private String freeDeliveryText;
    private boolean isActive;
}
