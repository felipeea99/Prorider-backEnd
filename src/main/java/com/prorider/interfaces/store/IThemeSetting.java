package com.prorider.interfaces.store;

import com.prorider.entities.store.ThemeSetting;

import java.util.List;
import java.util.UUID;

public interface IThemeSetting{

    ThemeSetting addTheme(ThemeSetting themeObject);
    ThemeSetting editTheme(ThemeSetting themeObject);
    Boolean deleteTheme(int themeId);
    Boolean setThemeToShow(int themeId);
    List<ThemeSetting> getAllThemes();
    ThemeSetting getThemeSettingActive();
    ThemeSetting getThemeById(int themeId);
    List<ThemeSetting> findAllThemes();

}