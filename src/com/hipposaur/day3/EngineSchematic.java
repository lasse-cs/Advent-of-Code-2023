package com.hipposaur.day3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EngineSchematic {

    private static Pattern numberPattern = Pattern.compile("\\d+");
    private static Pattern symbolPattern = Pattern.compile("[^0-9.]");

    private List<Symbol> symbols = new ArrayList<>();
    private List<Number> numbers = new ArrayList<>();

    public static void main(String[] args) {
        EngineSchematic schematic = new EngineSchematic();

        try (
            InputStream is = EngineSchematic.class.getResourceAsStream("input3.txt");
            Scanner scanner = new Scanner(is)
        ) {
            int row = 0;
            while (scanner.hasNextLine()) {
                schematic.parseLine(scanner.nextLine(), row);
                row += 1;
            }

            int sum = schematic.getPartNumbers().stream().mapToInt(Number::value).sum();
            System.out.println("The sum of the part numbers is: " + sum);

            int gearSum = schematic.getGearRatios().stream().mapToInt(Integer::intValue).sum();
            System.out.println("The sum of the gear ratios is: " + gearSum);
        } catch (IOException ioEx) {
            System.err.println("There was an error reading the input");
        }
       
    }

    public List<Integer> getGearRatios() {
        List<Integer> gearRatios = new ArrayList<>();
        symbols.stream().filter(s -> s.symbol().equals("*")).forEach(s -> {
            List<Number> adjacentNumbers = numbers.stream().filter(n -> n.isAdjacentToSymbol(s)).toList();
            if (adjacentNumbers.size() != 2)
                return;
            gearRatios.add(adjacentNumbers.get(0).value() * adjacentNumbers.get(1).value());
        });
        return gearRatios;
    }

    public List<Number> getPartNumbers() {
        return numbers.stream().filter(number -> {
            for (Symbol symbol : symbols) {
                if (number.isAdjacentToSymbol(symbol)) {
                    return true;
                }
            }
            return false;
        }).toList();
    }

    public void parseLine(String line, int row) {
        Matcher numberMatcher = numberPattern.matcher(line);
        numberMatcher.results().forEach(res -> {
            int start = res.start();
            int end = res.end() - 1;
            int value = Integer.parseInt(res.group());
            numbers.add(new Number(value, row, start, end));
        });

        Matcher symbolMatcher = symbolPattern.matcher(line);
        symbolMatcher.results().forEach(res -> {
            String symbol = res.group();
            int col = res.start();
            symbols.add(new Symbol(symbol, row, col));
        });
    }
}
