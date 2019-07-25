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
            langProps.put("start_wish", "Приятного использования!");

            //GENERAL
            langProps.put("first_subgroup", "I подгруппа");
            langProps.put("second_subgroup", "II подгруппа");
            langProps.put("back", "Назад");

            //DAYS
            //Shortened
            langProps.put("monday_shortened", "Пн");
            langProps.put("tuesday_shortened", "Вт");
            langProps.put("wednesday_shortened", "Ср");
            langProps.put("thursday_shortened", "Чт");
            langProps.put("friday_shortened", "Пт");
            langProps.put("saturday_shortened", "Сб");
            langProps.put("sunday_shortened", "Вс");

            //SETTINGS
            langProps.put("settings", "Настройки");

            //SCHEDULE MENU
            langProps.put("today", "На сегодня");
            langProps.put("tomorrow", "На завтра");
            langProps.put("week", "На неделю");
            langProps.put("next_week", "На следующую неделю");

            //SCHEDULE
            langProps.put("schedule", "Расписание");
            langProps.put("schedule_response", "На когда тебе надо?");
            langProps.put("time_schedule", "Время звоноков");
            langProps.put("day_off", "На этот день пар нет!");
            //langProps.put("subject", "Предмет: ");
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
        logger.debug("Trying to get localization string: "+ localizationField.name());
        String retrievedString = langProps.getProperty(localizationField.name().toLowerCase());

        if(retrievedString.length() > 0){
            logger.debug("Successfully redeemed localization string: " + localizationField.name());
            return retrievedString;
        }

        logger.error("Invalid LocalizationField, can't get string: ", localizationField.name());
        return "ОШИБКА! Нет предустанновленой строчки локализации. Напиши Тохе!";
    }
}
