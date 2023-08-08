package ru.duckcoder.util;

public enum RepeatType {
    ONCE,
    DAILY,
    WEEKLY(true);

    private final boolean isWeekly;

    RepeatType(Boolean isWeekly) {
        this.isWeekly = isWeekly;
    }

    RepeatType() {
        this.isWeekly = false;
    }

    public boolean isWeekly() {
        return isWeekly;
    }
}
