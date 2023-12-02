package com.hipposaur.day1;

import java.util.List;

public class TestCalibration {

    private record TestCase(String input, int expected) {

    }

    public static void main(String[] args) {
        Calibration cali = new Calibration();
        List<TestCase> tests = List.of(
            new TestCase("1abc2", 12),
            new TestCase("pqr3stu8vwx", 38),
            new TestCase("a1b2c3d4e5f", 15),
            new TestCase("treb7uchet", 77),
            new TestCase("two1nine", 29),
            new TestCase("eightwothree", 83),
            new TestCase("abcone2threexyz", 13),
            new TestCase("xtwone3four", 24),
            new TestCase("4nineeightseven2", 42),
            new TestCase("zoneight234", 14),
            new TestCase("7pqrstsixteen", 76),
            new TestCase("eightwo", 82)
        );

        tests.forEach(test -> {
            int actual = cali.getCalibrationForLine(test.input());
            System.out.println("Passes: " + (actual == test.expected) + " for input: " + test.input + " with actual: " + actual);
        });
    }
}
