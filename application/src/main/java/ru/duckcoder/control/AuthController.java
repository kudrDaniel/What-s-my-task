package ru.duckcoder.control;

import ru.duckcoder.control.application.Session;
import ru.duckcoder.control.application.Settings;
import ru.duckcoder.control.application.Settings.LastSession;

public class AuthController {
    private static Session actualSession;
    //private static User actualUser;

    public static void authorize(Settings settings) {
        //Registration new user or login exist user
        authentication(settings.getLastSession());
    }

    private static void authentication(LastSession lastSession) {

        String login;
        if (lastSession.getHasLastSession()) {
            login = lastSession.getLastLogin();
        } else {
            login = InputController.getLogin();
        }


    }
}
