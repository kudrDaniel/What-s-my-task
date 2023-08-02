package ru.duckcoder.util;

public enum ExitCodes {
    SUCCESS(0);

    private final Integer numCode;

    ExitCodes(Integer numCode) {
        this.numCode = numCode;
    }

    public Integer toInteger() {
        return this.numCode;
    }
}
