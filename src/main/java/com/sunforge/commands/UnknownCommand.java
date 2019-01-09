package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UnknownCommand {
    protected static void sendUnknownCommandMessage(Update passedUpdate){
        SendMessage snd = new SendMessage();
        snd.setChatId(passedUpdate.getMessage().getChatId());
        snd.setText("Сорри, я не знаю такой комманды! Попробуй какие-то из предложенных");

        try{
            UniScheduleBot.getInstance().execute(snd);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
