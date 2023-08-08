package ru.duckcoder.control;

import ru.duckcoder.control.application.Settings;

public class ApplicationController {
    private static Settings applicationSettings;
    public static void run() {
        //Initialize
        initializeApplication();
        //Authorization
        AuthController.authorize(applicationSettings);
        //Load data
    }

    private static void initializeApplication() {
        //Load settings.cfg
        applicationSettings = Settings.readApplicationSettings();
    }
}
