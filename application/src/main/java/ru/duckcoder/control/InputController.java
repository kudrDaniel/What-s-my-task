package ru.duckcoder.control;

import java.util.Scanner;

public final class InputController {

    private static String readString() {
        Scanner inputScanner = new Scanner(System.in);
        String result = inputScanner.nextLine();
        inputScanner.close();
        return result;
    }

    public static String getLogin() {
        return null;
    }
}
