package com.sunforge.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class LocalizationBundle {

    private static final Logger logger = LogManager.getLogger(LocalizationBundle.class);
    private static Properties langProps = null;

    private LocalizationBundle(){
        try{
            langProps = PropertiesStorage.getStandardMessages();
        }catch (IOException e){
            logger.error("Couldn't get standardMessages in LocalizationBundle cuz of IO", e);
            langProps = new Properties();    //Command messages

            //Used in /start
            langProps.put("start_choose_subgroup", "Выбери свою подгруппу:");

            //GENERAL
            langProps.put("first_subgroup", "I подгруппа");
            langProps.put("second_subgroup", "II подгруппа");
            langProps.put("back", "Назад");

            //SETTINGS
            langProps.put("settings", "Настройки");

            //VIEW
            langProps.put("subject", "Предмет: ");
            langProps.put("class", "Аудитория: ");
            langProps.put("teacher", "Препод: ");

            //ERRORS
            langProps.put("error_db_retrieve_subgroup", "ОШИБКА! Не удалось получить подгруппу из базы данных. Напиши Тохе!");
            langProps.put("error_db_retrieve_schedule", "ОШИБКА! Не удалось получить расписание из базы данных. Напиши Тохе!");
            langProps.put("error_input_invalid_command", "Сорри, я не знаю такой команды. Перепроверь ещё раз!");
            langProps.put("error_input_invalid_callback", "Сорри, что-то не так с колбеком. Напиши Тохе!");
        }
    }

    private static LocalizationBundle instance = null;

    public static LocalizationBundle getInstance(){
        if(instance == null){
            instance = new LocalizationBundle();
        }
        return instance;
    }

    public String getString(LocalizationField localizationField){
        logger.debug("Trying to get localization string: ", localizationField);
        switch (localizationField){
            ///start
            case START_CHOOSE_SUBGROUP:
                return langProps.getProperty("start_choose_subgroup");

            //General
            case FIRST_SUBGROUP:
                return langProps.getProperty("first_subgroup");
            case SECOND_SUBGROUP:
                return langProps.getProperty("second_subgroup");
            case BACK:
                return langProps.getProperty("back");

            //Settings
            case SETTINGS:
                return langProps.getProperty("settings");

            //View
            case SUBJECT:
                return langProps.getProperty("subject");
            case CLASS:
                return langProps.getProperty("class");
            case TEACHER:
                return langProps.getProperty("teacher");

            //Errors
            case ERROR_DB_RETRIEVE_SUBGROUP:
                return langProps.getProperty("error_db_retrieve_subgroup");
            case ERROR_DB_RETRIEVE_SCHEDULE:
                return langProps.getProperty("error_db_retrieve_schedule");
            case ERROR_INPUT_INVALID_COMMAND:
                return langProps.getProperty("error_input_invalid_command");
            case ERROR_INPUT_INVALID_CALLBACK:
                return langProps.getProperty("error_input_invalid_callback");
        }

        logger.error("Invalid LocalizationField, can't get string: ", localizationField);
        return "ОШИБКА! Нет предустанновленой строчки локализации. Напиши Тохе!";
    }
}
