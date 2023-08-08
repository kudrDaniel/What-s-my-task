package ru.duckcoder.util;

public enum TaskType {
    SIMPLE,
    BLOCK(true, false),
    CHAIN(false, true),
    BLOCK_CHAIN(true, true);

    private final Boolean isBlock;
    private final Boolean isChain;

    TaskType() {
        this.isBlock = false;
        this.isChain = false;
    }

    TaskType(Boolean isBlock, Boolean isChain) {
        this.isBlock = isBlock;
        this.isChain = isChain;
    }

    public Boolean getBlock() {
        return isBlock;
    }

    public Boolean getChain() {
        return isChain;
    }
}
