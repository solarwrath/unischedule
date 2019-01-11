package com.sunforge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ValidationHandler {
    private static final Logger logger = LogManager.getLogger(ValidationHandler.class);

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