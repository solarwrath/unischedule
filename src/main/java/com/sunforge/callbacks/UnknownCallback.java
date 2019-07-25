package com.sunforge.callbacks;

import com.sunforge.UniScheduleBot;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCallback {
    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

    public static void sendUnknownCallbackMessage(Update passedUpdate){
        SendMessage snd = new SendMessage()
                .setChatId(passedUpdate.getMessage().getChatId())
                .setText(localizationBundle.getString(LocalizationField.ERROR_INPUT_INVALID_CALLBACK));

        UniScheduleBot.getInstance().sendMessage(snd);
    }
}
