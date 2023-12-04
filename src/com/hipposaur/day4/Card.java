package com.hipposaur.day4;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public record Card(int id, Set<Integer> winningNumbers, Set<Integer> cardNumbers) {
    
    public static Card parse(String fromString) {
        String[] firstSplit = fromString.split(":");
        int id = Integer.parseInt(firstSplit[0].substring(5).trim());
        String[] secondSplit = firstSplit[1].trim().split("\\|");
        Set<Integer> winningNumbers = new HashSet<>();
        try (Scanner winningScanner = new Scanner(secondSplit[0])) {
            while (winningScanner.hasNextInt())
                winningNumbers.add(winningScanner.nextInt());
        }

        Set<Integer> cardNumbers = new HashSet<>();
        try (Scanner cardScanner = new Scanner(secondSplit[1])) {
            while (cardScanner.hasNextInt())
                cardNumbers.add(cardScanner.nextInt());
        }

        return new Card(id, winningNumbers, cardNumbers);
    }

    public int getScore() {
        return getIntersectionCount() > 0 ? (int) Math.pow(2, getIntersectionCount() - 1) : 0;
    }

    public long getIntersectionCount() {
        return cardNumbers.stream().filter(winningNumbers::contains).count();
    }
}
