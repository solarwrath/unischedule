package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import com.sunforge.properties.PropertiesStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.Properties;

public class UnknownCommand {
    public static void sendUnknownCommandMessage(Update passedUpdate){
        SendMessage snd = new SendMessage();
        snd.setChatId(passedUpdate.getMessage().getChatId());

        String unknownCommandMessage;

        try {
            Properties standardMessages = PropertiesStorage.getStandardMessages();
            unknownCommandMessage = standardMessages.getProperty("unknownCommand");
        }catch (IOException e){
            e.printStackTrace();
            unknownCommandMessage = "Сорри, я не знаю такой комманды! Попробуй какие-то из предложенных";
        }
        snd.setText(unknownCommandMessage);

        UniScheduleBot.getInstance().sendPassedMessage(snd);
    }
}
