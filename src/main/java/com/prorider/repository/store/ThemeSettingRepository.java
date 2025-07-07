package com.prorider.repository.store;

import com.prorider.entities.store.ThemeSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ThemeSettingRepository extends JpaRepository<ThemeSetting, Integer> {
    @Query("SELECT th FROM ThemeSetting th WHERE th.isActive = true")
    ThemeSetting findThemeActive();
}
