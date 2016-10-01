package org.agoenka.golist.models;

/**
 * Author: agoenka
 * Created At: 9/30/2016
 * Version: ${VERSION}
 */

public enum Priority {

    HIGH(1, "HIGH"),
    MEDIUM(2, "MEDIUM"),
    LOW(3, "LOW");

    private int value;
    private String description;

    public int get() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    Priority(int code, String description) {
        this.value = code;
        this.description = description;
    }

    public static Priority get(int code) {
        switch (code) {
            case 1: return HIGH;
            case 2: return MEDIUM;
            case 3: return LOW;
            default: return LOW;
        }
    }

    public static Priority get(String description) {
        switch (description) {
            case "HIGH": return HIGH;
            case "MEDIUM": return MEDIUM;
            case "LOW": return LOW;
            default: return LOW;
        }
    }
}