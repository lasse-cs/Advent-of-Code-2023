package com.hipposaur.day2.tests;

import java.util.List;

import com.hipposaur.day2.Color;
import com.hipposaur.day2.CubeGame;
import com.hipposaur.day2.DrawResult;

public class TestParsing {
    
    private record TestCase<T>(String input, T expected) {

        void run(T actual) {
            boolean passes =  actual.equals(expected);
            System.out.println("Passes: " + passes + " for input: " + input);
        }
    }

    public static void main(String[] args) {
        List<TestCase<Color>> colorTests = List.of(
            new TestCase<>("red", Color.RED),
            new TestCase<>("green", Color.GREEN),
            new TestCase<>("blue", Color.BLUE)
        );

        colorTests.forEach(test -> test.run(Color.parse(test.input)));

        List<TestCase<DrawResult>> drawTests = List.of(
            new TestCase<>("3 blue, 4 red", new DrawResult(4, 0, 3)),
            new TestCase<>("1 red, 2 green, 6 blue", new DrawResult(1, 2, 6)),
            new TestCase<>("2 green", new DrawResult(0, 2, 0))
        );

        drawTests.forEach(test -> test.run(DrawResult.parse(test.input)));

        List<TestCase<CubeGame>> gameTests = List.of(
            new TestCase<>(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", 
                new CubeGame(1, List.of(
                    new DrawResult(4, 0, 3),
                    new DrawResult(1, 2, 6),
                    new DrawResult(0, 2, 0)
                ))
            ),
            new TestCase<>(
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                new CubeGame(2, List.of(
                    new DrawResult(0, 2, 1),
                    new DrawResult(1, 3, 4),
                    new DrawResult(0, 1, 1)
                ))
            ),
            new TestCase<>(
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                new CubeGame(3, List.of(
                    new DrawResult(20, 8, 6),
                    new DrawResult(4, 13, 5),
                    new DrawResult(1, 5, 0)
                ))
            ),
            new TestCase<>(
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                new CubeGame(4, List.of(
                    new DrawResult(3, 1, 6),
                    new DrawResult(6, 3, 0),
                    new DrawResult(14, 3, 15)
                ))
            ),
            new TestCase<>(
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
                new CubeGame(5, List.of(
                    new DrawResult(6, 3, 1),
                    new DrawResult(1, 2, 2)
                ))
            )
            
        );

        gameTests.forEach(test -> test.run(CubeGame.parse(test.input)));
    }

}
