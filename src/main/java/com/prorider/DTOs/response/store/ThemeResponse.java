package com.prorider.DTOs.response.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ThemeResponse {
    private Integer themeId;
    private String primaryColor;
    private String secondaryColor;
    private String primaryTextColor;
    private String secondaryTextColor;
    private String backgroundColor;
    private String primaryHoverColor;
    private String secondaryHoverColor;
    private String backgroundTextColor;
    private String cardBackground;
    private String cardText;
    private boolean isActive;
}
