package com.hipposaur.day4.tests;

import java.util.List;
import java.util.Set;

import com.hipposaur.day4.Card;

public class TestParse {
    
    private record TestCase(String input, Card expected) {
        void run() {
            Card actual = Card.parse(input);
            System.out.println("Passes: " + (actual.equals(expected)) + " for input " + input);
        }
    }

    public static void main(String[] args) {
        List<TestCase> testCases = List.of(
            new TestCase("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                new Card(1, Set.of(41, 48, 83, 86, 17), Set.of(83, 86, 6, 31, 17, 9, 48, 53))
                ),
            new TestCase("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                new Card(2, Set.of(13, 32, 20, 16, 61), Set.of(61, 30, 68, 82, 17, 32, 24, 19))
                ),
            new TestCase("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                new Card(3, Set.of(1, 21, 53, 59, 44), Set.of(69, 82, 63, 72, 16, 21, 14, 1))
                ),
            new TestCase("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                new Card(4, Set.of(41, 92, 73, 84, 69), Set.of(59, 84, 76, 51, 58, 5, 54, 83))
                ),
            new TestCase("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                new Card(5, Set.of(87, 83, 26, 28, 32), Set.of(88, 30, 70, 12, 93, 22, 82, 36))
                ),
            new TestCase("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
                new Card(6, Set.of(31, 18, 13, 56, 72), Set.of(74, 77, 10, 23, 35, 67, 36, 11))
                )
        );

        testCases.forEach(TestCase::run);
    }
}
