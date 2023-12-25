package com.senelium.config;

public enum Browser {
    CHROME, FIREFOX;

    public static Browser of(String name) {
        switch (name.toLowerCase()) {
            case "chrome":
                return CHROME;
            case "firefox":
                return FIREFOX;
            default:
                throw new RuntimeException("Browser " + name + " is not defined!");
        }
    }
}
