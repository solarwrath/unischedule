package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import com.sunforge.db.ScheduleReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.Calendar;

public class DayScheduleCommand {

    private static final Logger logger = LogManager.getLogger(DayScheduleCommand.class);

    protected static void sendDayScheduleMessage(Update passedUpdate){
        SendMessage snd = new SendMessage();
        snd.setChatId(passedUpdate.getMessage().getChatId());

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        String textToSend;
        try {
            switch (currentDay) {
                case 2:
                    textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.MONDAY, isEvenWeek);
                    break;
                case 3:
                    textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.TUESDAY, isEvenWeek);
                    break;
                case 4:
                    textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.WEDNESDAY, isEvenWeek);
                    break;
                case 6:
                    textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.FRIDAY, isEvenWeek);
                    break;
                default:
                    textToSend = "На сегодня пар нет!";
                    break;
            }
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Couldn't get the schedule from db. Current day: " + currentDay);
            textToSend = "Ошибка! Не могу получить расписание с базы данных. Напиши Тохе чекнуть логи";
        }

        snd.setText(textToSend);

        try {
            UniScheduleBot.getInstance().execute(snd);
            logger.debug("Sended a message to user " + passedUpdate.getMessage().getFrom().getUserName() + " \"" + snd.getText() +"\"");
        } catch (TelegramApiException e) {
            logger.error("Telegram stuff fucked up");
            e.printStackTrace();
        }
    }
}
