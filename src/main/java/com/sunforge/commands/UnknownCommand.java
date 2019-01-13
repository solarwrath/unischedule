package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand {

    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

    public static void sendUnknownCommandMessage(Update passedUpdate){
        SendMessage snd = new SendMessage()
                            .setChatId(passedUpdate.getMessage().getChatId())
                            .setText(localizationBundle.getString(LocalizationField.ERROR_INPUT_INVALID_COMMAND));

        UniScheduleBot.getInstance().sendMessage(snd);
    }
}
