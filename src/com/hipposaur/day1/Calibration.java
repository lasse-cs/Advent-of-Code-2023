package com.hipposaur.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calibration {

    private static final List<String> digits = List.of(
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    );
    private static final List<String> reverseDigits = digits.stream().map(s -> new StringBuilder(s).reverse().toString()).toList();

    private static final Pattern p = Pattern.compile(("\\d|" + digits.stream().collect(Collectors.joining("|"))));
    private static final Pattern rp = Pattern.compile("\\d|" + reverseDigits.stream().collect(Collectors.joining("|")));
    
    public static void main(String[] args) {
        try (
            InputStream is = Calibration.class.getResourceAsStream("input1.txt");
            InputStreamReader inputReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputReader)
        ) {

            Calibration cali = new Calibration();
            int totalCalibration = reader.lines().mapToInt(cali::getCalibrationForLine).sum();
            System.out.println("The total calibration is: " + totalCalibration);
        } catch (IOException ioEx) {
            System.err.println("There was an error trying to read the input");
        }
    }

    public int getCalibrationForLine(String line) {
        Matcher m = p.matcher(line);
        List<MatchResult> matches = m.results().toList();
        if (matches.isEmpty())
            throw new RuntimeException("No matches found for " + line);

        String firstString = matches.get(0).group();

        Matcher m2 = rp.matcher(new StringBuilder(line).reverse().toString());
        matches = m2.results().toList();
        String lastString = matches.get(0).group();

        int firstDigit = digitFromResultString(firstString, false);
        int lastDigit = digitFromResultString(lastString, true);
        return 10 * firstDigit + lastDigit;
    }

    private int digitFromResultString(String resultString, boolean reverse) {
        if (resultString.length() == 1) {
            return Integer.parseInt(resultString);
        } else {
            return reverse ? reverseDigits.indexOf(resultString) + 1 : digits.indexOf(resultString) + 1;
        }
    }
}