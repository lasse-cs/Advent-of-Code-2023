package com.hipposaur.day3.tests;

import java.util.List;

import com.hipposaur.day3.EngineSchematic;

public class SchematicTest {
    
    private static String testSchematic = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
            """.trim();

    private static int expectedSum = 4361;
    private static int expectedProduct = 467835;

    public static void main(String[] args) {
        EngineSchematic schematic = new EngineSchematic();
        String[] lines = testSchematic.split("\n");
        for (int i = 0; i < lines.length; i++) {
            schematic.parseLine(lines[i], i);
        }
        int partSum = schematic.getPartNumbers().stream().mapToInt(num -> num.value()).sum();
        System.out.println("Part Passes: " + (partSum == expectedSum) + " with value: " + partSum);
        
        List<Integer> gearRatios = schematic.getGearRatios();
        int gearSum = gearRatios.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Gear Passes: " + (gearSum == expectedProduct) + " with value: " + gearSum);
    
    }
}
