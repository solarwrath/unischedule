package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import com.sunforge.db.ScheduleReader;
import com.sunforge.db.UserOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.Calendar;

public class DayScheduleCommand {

    private static final Logger logger = LogManager.getLogger(DayScheduleCommand.class);

    protected static void sendDayScheduleMessage(Update passedUpdate) {
        SendMessage snd = new SendMessage();
        snd.setChatId(passedUpdate.getMessage().getChatId());

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        String textToSend;

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
                    default:
                        textToSend = "На сегодня пар нет!";
                        break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Couldn't get the schedule from db. Current day: " + currentDay);
                textToSend = "Ошибка! Не могу получить расписание с базы данных. Напиши Тохе чекнуть логи";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Can't get user subgroup. User: " + passedUpdate.getMessage().getFrom().getId());
            textToSend = "Ошибка! Не могу получить подгруппу с базы данных. Напиши Тохе чекнуть логи";
        }

        snd.setText(textToSend);

        UniScheduleBot.getInstance().sendPassedMessage(snd);
    }
}
