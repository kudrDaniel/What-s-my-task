package ru.duckcoder.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.duckcoder.LoggerController;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class WriterController {
    public static void writeStringToFile(String string, Path actualPath) {
        OutputStream fileStream = OutputStream.nullOutputStream();
        try {
            fileStream = Files.newOutputStream(actualPath, StandardOpenOption.WRITE);
        } catch (Exception exception) {
            try {
                fileStream.close();
            } catch (IOException ioCloseStreamException) {
                LoggerController.getLogger().error(ioCloseStreamException);
            }
            LoggerController.getLogger().error(exception);
        }

        byte[] outputBytes = string.getBytes(StandardCharsets.UTF_8);
        try {
            fileStream.write(outputBytes);
        } catch (IOException ioByteWriteException) {
            try {
                fileStream.close();
            } catch (IOException ioCloseStreamException) {
                LoggerController.getLogger().error(ioCloseStreamException);
            }
            LoggerController.getLogger().error(ioByteWriteException);
        }

        try {
            fileStream.close();
        } catch (IOException ioCloseStreamException) {
            LoggerController.getLogger().error(ioCloseStreamException);
        }
    }
    public static void writeStringToFile(String string, String phonyPath) {
        Path actualPath = null;
        try {
            actualPath = Paths.get(URI.create(phonyPath));
        } catch (NullPointerException nullPathException) {
            LoggerController.getLogger().error(nullPathException);
        }

        writeStringToFile(string, actualPath);
    }

    public static void saveEncryptedPassword(byte[] encryptedPassword)
            throws URISyntaxException, IOException {
        Path passwordPath = Paths.get(
               Objects.requireNonNull(WriterController.class.getClassLoader().getResource("ythspso.a")).toURI()
        );

        OutputStream passwordStream = OutputStream.nullOutputStream();

        try {
            passwordStream = Files.newOutputStream(passwordPath, StandardOpenOption.WRITE);

            new ObjectMapper().writeValue(passwordStream, encryptedPassword);
        } finally {
            passwordStream.close();
        }
    }
}
