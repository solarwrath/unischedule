package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand {

    private static final Logger logger = LogManager.getLogger(UnknownCommand.class);

    public static void sendUnknownCommandMessage(Update passedUpdate){
        SendMessage snd = new SendMessage();
        snd.setChatId(passedUpdate.getMessage().getChatId());

        String unknownCommandMessage = LocalizationBundle.getInstance().getString(LocalizationField.ERROR_INPUT_INVALID_COMMAND);
        snd.setText(unknownCommandMessage);

        UniScheduleBot.getInstance().sendMessage(snd);
    }
}
