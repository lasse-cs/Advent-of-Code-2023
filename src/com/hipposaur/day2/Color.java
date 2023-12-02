package com.hipposaur.day2;

public enum Color {
    RED, GREEN, BLUE;

    public static Color parse(String fromString) {
        return Color.valueOf(fromString.toUpperCase());
    }
}
