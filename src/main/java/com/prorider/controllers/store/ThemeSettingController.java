package com.prorider.controllers.store;

import com.prorider.entities.store.ThemeSetting;
import com.prorider.services.store.ThemeSettingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/themes")
public class ThemeSettingController {
    private final ThemeSettingService themeSettingService;

    public ThemeSettingController(ThemeSettingService themeSettingService) {
        this.themeSettingService = themeSettingService;
    }

    @PostMapping("/")
    public ThemeSetting addTheme(@RequestBody ThemeSetting themeSetting) {
        return themeSettingService.addTheme(themeSetting);
    }

    @PutMapping("/")
    public ThemeSetting editTheme(@RequestBody ThemeSetting themeSetting) {
        return themeSettingService.editTheme(themeSetting);
    }

    @GetMapping("/retrieveAllThemes")
    public List<ThemeSetting> getAllThemes() {
        return themeSettingService.getAllThemes();
    }
    @GetMapping("/{themeId}/setThemeToShow")
    public Boolean setThemeToShow(@PathVariable int themeId) {
        return themeSettingService.setThemeToShow(themeId);
    }
    @GetMapping("/{themeId}")
    public Boolean getThemeSettingActive(@PathVariable int themeId) {
        return themeSettingService.deleteTheme(themeId);
    }

    @GetMapping("/themesList")
    public List<ThemeSetting> findAllThemesByStoreId() {
        return themeSettingService.findAllThemes();
    }



}
