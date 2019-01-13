package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TimeCommand {

    private static final Logger logger = LogManager.getLogger(TimeCommand.class);

    public static void sendTimeMessage(Update passedUpdate){
        SendMessage snd = new SendMessage();
        snd.setChatId(passedUpdate.getMessage().getChatId());

        String timeMessage = "Пара   Начало - Конец\n" +
                "   1             9:00 − 10:20\n" +
                "   2           10:30 − 11:50\n" +
                "   3           12:30 − 13:50\n" +
                "   4           14:00 − 15:20\n" +
                "   5           15:30 − 16:50";
        snd.setText(timeMessage);

        logger.debug("Set timeMessage");
        UniScheduleBot.getInstance().sendMessage(snd);
    }
}
