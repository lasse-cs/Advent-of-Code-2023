package com.hipposaur.day2;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public record CubeGame(int id, List<DrawResult> results) {

    private static final EnumMap<Color, Integer> maxCubes = new EnumMap<>(Map.of(
        Color.RED, 12,
        Color.GREEN, 13, 
        Color.BLUE, 14)
    );

    public static CubeGame parse(String fromString) {
        String[] parts = fromString.split(":");
        String gameString = parts[0];
        String[] instructions = parts[1].trim().split(";");

        int id = Integer.parseInt(gameString.trim().split(" ")[1]);
        List<DrawResult> results = Arrays.stream(instructions).map(DrawResult::parse).toList();

        return new CubeGame(id, results);
    }

    public boolean isPossible() {
        boolean isPossible = true; 

        for (Color color : Color.values()) 
            isPossible &= isColorPossible(color);

        return isPossible;
    }

    private int getMaxForColor(Color color) {
        return results.stream().mapToInt(r -> r.get(color)).max().orElse(0);
    }

    private boolean isColorPossible(Color color) {
        return getMaxForColor(color) <= maxCubes.get(color);
    }

    public int getPower() {
        int power = 1;
        for (Color color: Color.values())
            power *= getMaxForColor(color);
        return power;
    }
}
