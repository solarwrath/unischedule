package com.sunforge.callbacks;

import com.sunforge.UniScheduleBot;
import com.sunforge.db.UserOperations;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.SQLException;

class ChangeSubgroupCallback {

    private static final Logger logger = LogManager.getLogger(ChangeSubgroupCallback.class);
    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

    static void changeSubgroupCallback(Update passedUpdate, int chosenSubgroup) {
        long chat_id = passedUpdate.getCallbackQuery().getMessage().getChatId();
        try {
            UserOperations.changeSubgroup(chat_id, chosenSubgroup);
        } catch (SQLException e) {
            logger.error("Couldn't get the subgroup of " + chat_id, e);
            SendMessage snd = new SendMessage()
                    .setChatId(passedUpdate.getCallbackQuery().getMessage().getChatId())
                    .setText(localizationBundle.getString(LocalizationField.ERROR_DB_RETRIEVE_SUBGROUP));
            UniScheduleBot.getInstance().sendMessage(snd);
        }
    }
}