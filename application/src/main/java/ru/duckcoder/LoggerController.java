package ru.duckcoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggerController {
    private static final Logger LOGGER = LogManager.getLogger();

    public static Logger getLogger() {
        return LOGGER;
    }
}
