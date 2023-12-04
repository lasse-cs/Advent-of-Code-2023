package com.hipposaur.day4.tests;

import java.util.List;
import java.util.Set;

import com.hipposaur.day4.Card;

public class TestScore {
    
    private record TestCase(Card card, int expectedScore) {
        void run() {
            System.out.println("Passes: " + (card.getScore() == expectedScore) + " for card " + card);
        }
    }

    public static void main(String[] args) {
        List<TestCase> testCards = List.of(
            new TestCase(
                new Card(1, Set.of(41, 48, 83, 86, 17), Set.of(83, 86, 6, 31, 17, 9, 48, 53))
                , 8),
            new TestCase(
                new Card(2, Set.of(13, 32, 20, 16, 61), Set.of(61, 30, 68, 82, 17, 32, 24, 19))
                , 2),
            new TestCase(
                new Card(3, Set.of(1, 21, 53, 59, 44), Set.of(69, 82, 63, 72, 16, 21, 14, 1))
                , 2),
            new TestCase(
                new Card(4, Set.of(41, 92, 73, 84, 69), Set.of(59, 84, 76, 51, 58, 5, 54, 83))
                , 1),
            new TestCase(
                new Card(5, Set.of(87, 83, 26, 28, 32), Set.of(88, 30, 70, 12, 93, 22, 82, 36))
                , 0),
            new TestCase(
                new Card(6, Set.of(31, 18, 13, 56, 72), Set.of(74, 77, 10, 23, 35, 67, 36, 11))
                , 0)
            
        );

        testCards.forEach(TestCase::run);
    }
}
