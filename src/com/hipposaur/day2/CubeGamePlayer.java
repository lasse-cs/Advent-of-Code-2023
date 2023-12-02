package com.hipposaur.day2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CubeGamePlayer {
    
    public static void main(String[] args) {
        try (
            InputStream in = CubeGamePlayer.class.getResourceAsStream("input2.txt");
            Scanner scanner = new Scanner(in)
        ) {
            int totalPossible = 0;
            int totalPower = 0;
            while (scanner.hasNextLine()) {
                CubeGame game = CubeGame.parse(scanner.nextLine());
                if (game.isPossible())
                    totalPossible += game.id();
                totalPower += game.getPower();
            }
            System.out.println("The sum of possible game IDs is: " + totalPossible);
            System.out.println("The sum of powers is: " + totalPower);
        } catch (IOException ioEx) {
            System.err.println("There was an issue with getting the input.");
        }
    }
}
