package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import com.sunforge.db.ScheduleReader;
import com.sunforge.db.UserOperations;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.Calendar;

public class DayScheduleCommand {

    private static final Logger logger = LogManager.getLogger(DayScheduleCommand.class);

    public static void sendDayScheduleMessage(Update passedUpdate) {
        SendMessage snd = new SendMessage();
        snd.setChatId(passedUpdate.getMessage().getChatId());

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        String textToSend;
        LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

        try {
            short userSubgroup = UserOperations.getSubgroup(passedUpdate.getMessage().getFrom().getId());
            try {
                switch (currentDay) {
                    case 2:
                        textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.MONDAY, isEvenWeek, userSubgroup);
                        break;
                    case 3:
                        textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.TUESDAY, isEvenWeek, userSubgroup);
                        break;
                    case 4:
                        textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.WEDNESDAY, isEvenWeek, userSubgroup);
                        break;
                    case 6:
                        textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.FRIDAY, isEvenWeek, userSubgroup);
                        break;

                    //If Thursday, Saturday or Sunday, display that it is a day off
                    default:
                        textToSend = localizationBundle.getString(LocalizationField.DAYOFF);
                        break;
                }
            } catch (SQLException e) {
                logger.error("Couldn't get the schedule from db. Current day: " + currentDay, e);
                textToSend = localizationBundle.getString(LocalizationField.ERROR_DB_RETRIEVE_SCHEDULE);
            }
        } catch (SQLException e) {
            logger.error("Can't get user subgroup. User: " + passedUpdate.getMessage().getFrom().getId(), e);
            textToSend = localizationBundle.getString(LocalizationField.ERROR_DB_RETRIEVE_SUBGROUP);
        }

        snd.setText(textToSend);

        UniScheduleBot.getInstance().sendPassedMessage(snd);
    }
}
