package com.hipposaur.day2;

import java.util.EnumMap;
import java.util.Map;

public record DrawResult(int red, int green, int blue) {
    
    public int get(Color color) {
        return switch (color) {
            case BLUE -> blue;
            case GREEN -> green;
            case RED -> red;
        };
    }

    /**
     * Expected form of the input String is 
     * i red, j green, k blue
     */
    public static DrawResult parse(String fromString) {
        String[] parts = fromString.split(", ");
        Map<Color, Integer> map = new EnumMap<>(Color.class);
        for (String part : parts) {
            String[] partPart = part.trim().split(" ");
            Color color = Color.parse(partPart[1]);
            int value = Integer.parseInt(partPart[0]);
            map.put(color, value);
        }

        return new DrawResult(
            map.getOrDefault(Color.RED, 0),
            map.getOrDefault(Color.GREEN, 0),
            map.getOrDefault(Color.BLUE, 0));
    }
}
