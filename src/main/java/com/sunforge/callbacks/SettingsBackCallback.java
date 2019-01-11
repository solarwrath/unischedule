package com.sunforge.callbacks;

import com.sunforge.UniScheduleBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SettingsBackCallback {

    public static void settingsBack(Update passedUpdate){
        long chat_id = passedUpdate.getCallbackQuery().getMessage().getChatId();
        Integer message_id = passedUpdate.getCallbackQuery().getMessage().getMessageId();

        DeleteMessage deleteMessage = new DeleteMessage().setChatId(chat_id).setMessageId(message_id);

        UniScheduleBot.getInstance().deleteMessage(deleteMessage);
    }
}
