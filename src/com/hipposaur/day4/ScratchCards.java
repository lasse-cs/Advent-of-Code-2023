package com.hipposaur.day4;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ScratchCards {

    Map<Card, Integer> cardCountMap = new HashMap<>();

    public static void main(String[] args) {
        ScratchCards cardSet = new ScratchCards();
        try (
            InputStream is = ScratchCards.class.getResourceAsStream("input4.txt");
            Scanner scanner = new Scanner(is)
        ) {
            while (scanner.hasNextLine()) {
                cardSet.addCard(Card.parse(scanner.nextLine()));
            }
        } catch (IOException ioEx) {
            System.err.println("There was an error reading the input file.");
        }

        int totalPoints = cardSet.getCards().stream().mapToInt(Card::getScore).sum();
        System.out.println("The total points of the scratch cards is " + totalPoints);

        int totalCards = cardSet.evaluateAllCards();
        System.out.println("The total number of cards in the end is " + totalCards);
    }

    public void addCard(Card card) {
        cardCountMap.put(card, 1);
    }

    public Set<Card> getCards() {
        return cardCountMap.keySet();
    }

    public Card getCardForIndex(int id) {
        return cardCountMap.keySet().stream().filter(c -> c.id() == id).findFirst().get();
    }

    private void evaluateCard(Card card) {
        long intersectionCount = card.getIntersectionCount();
        int startId = card.id();
        if (intersectionCount == 0)
            return;
        int cardCount = cardCountMap.get(card);
        
        for (int i = 1; i <= intersectionCount; i++) {
            Card nextCard = getCardForIndex(startId + i);
            int nextCardCount = cardCountMap.get(nextCard);
            cardCountMap.put(nextCard, nextCardCount + cardCount);
        }
    }

    public int evaluateAllCards() {
        for (int i = 0; i < cardCountMap.size(); i++) {
            evaluateCard(getCardForIndex(i + 1));
        }
        
        return cardCountMap.values().stream().mapToInt(Integer::intValue).sum();
    }


}
