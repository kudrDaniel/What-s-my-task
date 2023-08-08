package ru.duckcoder;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import ru.duckcoder.control.ApplicationController;
import ru.duckcoder.control.ReadController;
import ru.duckcoder.user.Registration;
import ru.duckcoder.util.encrypt.Password;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Application {
    public static void main(String[] args) {
        ApplicationController.run();
    }

    public void testInjection() throws URISyntaxException {
        Path passwordPath = Paths.get(
                Objects.requireNonNull(this.getClass().getClassLoader().getResource("sample1.txt")).toURI()
        ).toAbsolutePath();

        String sample = ReadController.readFileToString(passwordPath);

        System.out.println(sample);
    }
}
