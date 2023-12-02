package com.hipposaur.day2;

import java.util.List;

public class TestGames {
    
    private record TestCase(CubeGame game, boolean expected, int power) {
        void runPossible() {
            System.out.println("Passes Possible: " + (game.isPossible() == expected) + " for id " + game.id());
        }

        void runPower() {
            System.out.println("Passes Power: " + (game.getPower() == power) + " for id " + game.id());
        }
    }

    public static void main(String[] args) {
        List<TestCase> gameTests = List.of(
            new TestCase(
                new CubeGame(1, List.of(
                        new DrawResult(4, 0, 3),
                        new DrawResult(1, 2, 6),
                        new DrawResult(0, 2, 0)
                )), true, 48),
            new TestCase(
                new CubeGame(2, List.of(
                        new DrawResult(0, 2, 1),
                        new DrawResult(1, 3, 4),
                        new DrawResult(0, 1, 1)
                )), true, 12),
            new TestCase(
                new CubeGame(3, List.of(
                    new DrawResult(20, 8, 6),
                    new DrawResult(4, 13, 5),
                    new DrawResult(1, 5, 0)
            )), false, 1560),
            new TestCase(
                new CubeGame(4, List.of(
                        new DrawResult(3, 1, 6),
                        new DrawResult(6, 3, 0),
                        new DrawResult(14, 3, 15)
                )), false, 630),
            new TestCase(
                new CubeGame(5, List.of(
                    new DrawResult(6, 3, 1),
                    new DrawResult(1, 2, 2)
            )), true, 36)
        );

        gameTests.forEach(TestCase::runPossible);
        gameTests.forEach(TestCase::runPower);
    }
}
