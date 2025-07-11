package com.prorider.services.store;

import com.prorider.entities.store.ThemeSetting;
import com.prorider.exceptions.BadRequestException;
import com.prorider.interfaces.store.IThemeSetting;
import com.prorider.repository.store.ThemeSettingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ThemeSettingService implements IThemeSetting {

    private final ThemeSettingRepository themeSettingRepository;

    public ThemeSettingService(ThemeSettingRepository themeSettingRepository){
        this.themeSettingRepository = themeSettingRepository;
    }

    /// Adds a theme object in database
    @Override
    public ThemeSetting addTheme(ThemeSetting themeObject) {
        return themeSettingRepository.save(themeObject);
    }

    /// Edits a theme object base on the give theme object
    @Override
    public ThemeSetting editTheme(ThemeSetting themeObject) {
        //ThemeId validation
        ThemeSetting theme = themeSettingRepository.findById(themeObject.getThemeId())
                .orElseThrow(() ->new BadRequestException("themeId not found in database"));

        theme.setPrimaryColor(themeObject.getPrimaryColor());
        theme.setSecondaryColor(themeObject.getSecondaryColor());
        theme.setPrimaryTextColor(themeObject.getPrimaryTextColor());
        theme.setSecondaryTextColor(themeObject.getSecondaryTextColor());
        theme.setBackgroundColor(themeObject.getBackgroundColor());
        theme.setPrimaryHoverColor(themeObject.getPrimaryHoverColor());
        theme.setSecondaryHoverColor(themeObject.getSecondaryHoverColor());
        theme.setBackgroundTextColor(themeObject.getBackgroundTextColor());
        theme.setCardBackground(themeObject.getCardBackground());
        theme.setCardText(themeObject.getCardText());
        theme.setFreeDeliveryText(themeObject.getFreeDeliveryText());

        return themeSettingRepository.save(theme);

    }

    /// Deletes a theme object base on the give themeId
    @Override
    public Boolean deleteTheme(int themeId) {
        //ThemeId validation
        themeSettingRepository.findById(themeId)
                .orElseThrow(() ->new BadRequestException("themeId not found in database"));
        //remove the object from the database
        themeSettingRepository.deleteById(themeId);
        return true;
    }

    @Override
    public Boolean setThemeToShow(int themeId) {
        //ThemeId validation
        ThemeSetting theme = themeSettingRepository.findById(themeId)
                .orElseThrow(() ->new BadRequestException("themeId not found in database"));
        //Set the theme Chosen to TRUE
        theme.setActive(true);
        return true;
    }

    /// Get All the Themes for ADMIN
    @Override
    public List<ThemeSetting> getAllThemes() {
       return themeSettingRepository.findAll();
    }

    ///Edits the Setting that is active in a store, it is marked as inactive (FALSE)
    @Override
    public ThemeSetting getThemeSettingActive() {
        //find the object with the Theme.Active == to "TRUE"
         ThemeSetting theme =  themeSettingRepository.findThemeActive();
         //Set the theme.Active to "FALSE" and save it in the database
        theme.setActive(false);
        return themeSettingRepository.save(theme);
    }

    /// Retrieves an object from the database base on a given themeId
    @Override
    public ThemeSetting getThemeById(int themeId) {
        return themeSettingRepository.findById(themeId)
                .orElseThrow(() ->new BadRequestException("themeId not found in database"));
    }

    @Override
    public List<ThemeSetting> findAllThemes() {
        return themeSettingRepository.findAll();
    }
}
