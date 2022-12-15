package com.example.demo;

import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * The object of this class is responsible to maintain and change the state of the application theme.
 *
 * @author Donald Agustino
 */
public class ThemeController {
    private static ThemeController themeController = null;

    private HashMap<String, Theme> theme;

    private Theme currentTheme;

    private String currentThemeName;

    public static ThemeController getInstance() {
        if (themeController == null) {
            themeController = new ThemeController();
            return themeController;
        }
        return themeController;
    }

    public ThemeController() {
        this.theme = new HashMap<String, Theme>();

        Theme themes[] = new Theme[4];

        themes[0] = new Theme();
        themes[0].setBackgroundColor(Color.rgb(0, 222, 255));
        themes[0].addCellColorMap(0, Color.rgb(255, 255, 255, 0.5));
        themes[0].addCellColorMap(2, Color.rgb(0, 195, 255));
        themes[0].addCellColorMap(4, Color.rgb(0, 181, 255));
        themes[0].addCellColorMap(8, Color.rgb(0, 165, 255));
        themes[0].addCellColorMap(16, Color.rgb(0, 148, 255));
        themes[0].addCellColorMap(32, Color.rgb(0, 130, 255));
        themes[0].addCellColorMap(64, Color.rgb(0, 109, 255));
        themes[0].addCellColorMap(128, Color.rgb(0, 84, 161));
        themes[0].addCellColorMap(256, Color.rgb(0, 67, 151));
        themes[0].addCellColorMap(512, Color.rgb(0, 56, 140));
        themes[0].addCellColorMap(1024, Color.rgb(0, 34, 118));
        themes[0].addCellColorMap(2048, Color.rgb(0, 22, 106));

        theme.put("BLUE OCEAN", themes[0]);

        themes[1] = new Theme();
        themes[1].setBackgroundColor(Color.rgb(120, 20, 100, 0.2));
        themes[1].addCellColorMap(0, Color.rgb(224, 226, 226, 0.7));
        themes[1].addCellColorMap(2, Color.rgb(232, 255, 100, 0.5));
        themes[1].addCellColorMap(4, Color.rgb(232, 220, 50, 0.5));
        themes[1].addCellColorMap(8, Color.rgb(232, 200, 44, 0.8));
        themes[1].addCellColorMap(16, Color.rgb(232, 170, 44, 0.8));
        themes[1].addCellColorMap(32, Color.rgb(180, 120, 44, 0.7));
        themes[1].addCellColorMap(64, Color.rgb(180, 100, 44, 0.7));
        themes[1].addCellColorMap(128, Color.rgb(180, 80, 44, 0.7));
        themes[1].addCellColorMap(256, Color.rgb(180, 60, 44, 0.8));
        themes[1].addCellColorMap(512, Color.rgb(180, 30, 44, 0.8));
        themes[1].addCellColorMap(1024, Color.rgb(250, 0, 44, 0.8));
        themes[1].addCellColorMap(2048, Color.rgb(250, 0, 0, 1));

        theme.put("RED NUANCE", themes[1]);
    }

    public Theme getThemeByName(String name) {
        return this.theme.get(name);
    }

    public void setCurrentThemeByName(String name) {
        this.currentThemeName = name;
        this.currentTheme = this.theme.get(name);
    }

    public Theme getCurrentTheme() { return this.currentTheme; }

    public String getCurrentThemeName() { return this.currentThemeName; };
}
