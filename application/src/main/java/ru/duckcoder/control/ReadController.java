package ru.duckcoder.control;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.duckcoder.LoggerController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ReadController {

    public static String readFileToString(Path actualPath) {
        InputStream fileStream = InputStream.nullInputStream();
        try {
            fileStream = Files.newInputStream(actualPath, StandardOpenOption.READ);
        } catch (Exception exception) {
            try {
                fileStream.close();
            } catch (IOException ioCloseStreamException) {
                LoggerController.getLogger().error(ioCloseStreamException);
            }
            LoggerController.getLogger().error(exception);
        }

        byte[] resultBytes = ("Value not changed").getBytes(StandardCharsets.UTF_8);
        try {
            byte actualByte;
            List<Byte> tempByteList = new ArrayList<>();
            while((actualByte = (byte) fileStream.read()) != -1) {
                tempByteList.add(actualByte);
            }
            resultBytes = new byte[tempByteList.size()];
            for (int index = 0; index < resultBytes.length; index++) {
                resultBytes[index] = tempByteList.get(index);
            }
        } catch (IOException ioByteReadException) {
            try {
                fileStream.close();
            } catch (IOException ioCloseStreamException) {
                LoggerController.getLogger().error(ioCloseStreamException);
            }
            LoggerController.getLogger().error(ioByteReadException);
        }

        try {
            fileStream.close();
        } catch (IOException ioCloseStreamException) {
            LoggerController.getLogger().error(ioCloseStreamException);
        }

        return new String(resultBytes, StandardCharsets.UTF_8);
    }
    public static String readFileToString(String phonyPath) {
        Path actualPath = null;
        try {
            actualPath = Paths.get(URI.create(phonyPath));
        } catch (NullPointerException nullPathException) {
            LoggerController.getLogger().error(nullPathException);
        }

        return readFileToString(actualPath);
    }

    public static byte[] deserializePassword()
            throws URISyntaxException, IOException {
        Path passwordPath = Paths.get(
                ReadController.class.getClassLoader().getResource("ythspso.a").toURI()
        );

        byte[] passwordString = null;

        InputStream passwordStream = InputStream.nullInputStream();

        try {
            passwordStream = Files.newInputStream(passwordPath, StandardOpenOption.READ);

            passwordString = new ObjectMapper().readValue(passwordStream, new TypeReference<>() {});
        } finally {
            passwordStream.close();
        }

        return passwordString;
    }
}
