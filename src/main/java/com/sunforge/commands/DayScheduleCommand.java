package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import com.sunforge.db.ScheduleReader;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.DayOfWeek;
import java.util.Calendar;

public class DayScheduleCommand {
    protected static void sendDayScheduleMessage(Update passedUpdate){
        SendMessage snd = new SendMessage();
        snd.setChatId(passedUpdate.getMessage().getChatId());

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        String textToSend;

        switch (currentDay){
            case 2:
                textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.MONDAY);
                break;
            case 3:
                textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.TUESDAY);
                break;
            case 4:
                textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.WEDNESDAY);
                break;
            case 6:
                textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.FRIDAY);
                break;
            default:
                textToSend = "На сегодня пар нет!";
                break;
        }

        snd.setText(textToSend);

        try {
            UniScheduleBot.getInstance().execute(snd);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
