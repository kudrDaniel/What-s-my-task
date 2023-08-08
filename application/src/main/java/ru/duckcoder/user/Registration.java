package ru.duckcoder.user;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

public class Registration {
    public UUID uuid;
    public String pass;

    public Registration(String name, String surname, String email, Date birthDate, String pass) {
        this.uuid = UUID.nameUUIDFromBytes((surname + name + email + birthDate).getBytes(StandardCharsets.UTF_8));
        this.pass = pass;
    }


}
