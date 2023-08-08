package ru.duckcoder.control.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.duckcoder.LoggerController;
import ru.duckcoder.control.ReadController;
import ru.duckcoder.control.WriterController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class Settings {
    public static class LastSession {
        private Boolean hasLastSession;
        private String lastLogin;
        private UUID lastUUID;

        public LastSession(Boolean hasLastSession, String lastLogin, UUID lastUUID) {
            this.hasLastSession = hasLastSession;
            this.lastLogin = lastLogin;
            this.lastUUID = lastUUID;
        }

        public LastSession() {};

        public boolean getHasLastSession() {
            return hasLastSession;
        }

        public void setHasLastSession(boolean hasLastSession) {
            this.hasLastSession = hasLastSession;
        }

        public String getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(String lastLogin) {
            this.lastLogin = lastLogin;
        }

        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (obj instanceof LastSession) {
                int thisHashCode = this.hashCode();
                int objHashCode = ((LastSession) obj).hashCode();
                if (thisHashCode == objHashCode) {
                    return true;
                }
                return this.hasLastSession.equals(((LastSession) obj).hasLastSession)
                        && this.lastLogin.equals(((LastSession) obj).lastLogin);
            }
            return false;
        }

        @Override
        public String toString() {
            return ":{"
                    + "hasLastSession:" + hasLastSession + ","
                    + "lastLogin:" + lastLogin + "}";
        }
    }

    private static final Path SETTINGS_PATH = Paths.get(
            System.getProperty("user.dir"),
            "settings.json"
    ).toAbsolutePath();

    private LastSession lastSession;

    public Settings(LastSession lastSession) {
        this.lastSession = lastSession;
    }
    public Settings() {}

    public LastSession getLastSession() {
        return lastSession;
    }

    public void setLastSession(LastSession lastSession) {
        this.lastSession = lastSession;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "{"
                + "lastSession:" + lastSession + "}";
    }

    public static Settings readApplicationSettings() {
        if(Files.notExists(SETTINGS_PATH)) {
            createDefaultSettings();
        }

        String jsonSettings = ReadController.readFileToString(SETTINGS_PATH);

        ObjectMapper objectMapper = new ObjectMapper();
        Settings resultSettings = new Settings();
        try {
            resultSettings = objectMapper.readValue(jsonSettings, new TypeReference<>() {});
        } catch (JsonProcessingException jsonReadSettingsException) {
            resultSettings.setLastSession(new Settings.LastSession(
                    true,
                    "",
                    UUID.randomUUID()
            ));
            LoggerController.getLogger().error(jsonReadSettingsException);
        }
        return resultSettings;
    }

    private static void createDefaultSettings() {
        try {
            Files.createFile(SETTINGS_PATH);
        } catch (IOException ioCreateDefaultSettingsFileException) {
            LoggerController.getLogger().error(ioCreateDefaultSettingsFileException);
        }

        Settings.LastSession defaultLastSession = new Settings.LastSession(false, null, UUID.randomUUID());
        Settings defaultSettings = new Settings(defaultLastSession);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "Json Processing Failure";
        try {
            jsonString = objectMapper.writeValueAsString(defaultSettings);
        } catch (JsonProcessingException jsonWriteSettingsException) {
            LoggerController.getLogger().error(jsonWriteSettingsException);
        }

        WriterController.writeStringToFile(jsonString, SETTINGS_PATH);
    }
}
