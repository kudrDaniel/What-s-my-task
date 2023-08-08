package ru.duckcoder.util.encrypt;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.duckcoder.control.WriterController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

public class Password {
    @JsonProperty("someShit")
    byte[] encryptedPassword;

    public Password(byte[] encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
    public Password() {}

    public void encryptPassword(UUID uuid, String password)
            throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        this.encryptedPassword = digest.digest((password + uuid).getBytes(StandardCharsets.UTF_8));
    }

    public byte[] getEncryptedPassword() {
        return this.encryptedPassword;
    }

    public void savePassword()
            throws URISyntaxException, IOException {
        WriterController.saveEncryptedPassword(this.encryptedPassword);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Password) {
            return Arrays.equals(this.encryptedPassword, ((Password) obj).encryptedPassword);
        }
        throw new IllegalArgumentException();
    }
}
