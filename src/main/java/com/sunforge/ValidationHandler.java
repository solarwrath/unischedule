package com.sunforge;

public class ValidationHandler {
    protected static boolean validateCommand(String givenString) {
        return (givenString.startsWith("/") && countCharOccurence(givenString, '/') == 1);
    }

    private static int countCharOccurence(String givenString, char givenChar) {
        int result = 0;

        for (char currentChar : givenString.toCharArray()) {
            if (currentChar == givenChar) {
                result++;
            }
        }

        return result;
    }
}