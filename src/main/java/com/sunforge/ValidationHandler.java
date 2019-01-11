package com.sunforge;

import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class ValidationHandler {
    private static final Logger logger = LogManager.getLogger(ValidationHandler.class);

    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

    private static List<String> listOfCommands = new ArrayList<>();
    static {
        listOfCommands.add("/start");
        listOfCommands.add(localizationBundle.getString(LocalizationField.SCHEDULE));
        listOfCommands.add(localizationBundle.getString(LocalizationField.TIME_SCHEDULE));
        listOfCommands.add(localizationBundle.getString(LocalizationField.SETTINGS));
    }

    public static boolean validateCommand(String givenString) {
        return listOfCommands.contains(givenString);
    }
}