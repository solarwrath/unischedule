package com.sunforge.callbacks;

import com.sunforge.UniScheduleBot;
import com.sunforge.db.UserOperations;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class StartCallback {

    private static final Logger logger = LogManager.getLogger(StartCallback.class);
    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

    static void startCallback(Update passedUpdate, int chosenSubgroup){
        try {
            CallbackQuery callbackQuery = passedUpdate.getCallbackQuery();
            UserOperations.changeSubgroup(callbackQuery.getMessage().getChatId(), chosenSubgroup);

            SendMessage sndMessage = new SendMessage().
                                        setChatId(callbackQuery.getMessage().getChatId()).
                                        setText(localizationBundle.getString(LocalizationField.START_WISH));

            ReplyKeyboardMarkup replyKeyboard = new ReplyKeyboardMarkup();
            List<KeyboardRow> listOfRows = new ArrayList<>();

            //Main keyboard with 2 rows
            //          Schedule
            //Time Schedule / Settings
            KeyboardRow firstRow = new KeyboardRow();
            firstRow.add(localizationBundle.getString(LocalizationField.SCHEDULE));

            KeyboardRow secondRow = new KeyboardRow();
            secondRow.add(0,localizationBundle.getString(LocalizationField.TIME_SCHEDULE));
            secondRow.add(1, localizationBundle.getString(LocalizationField.SETTINGS));

            listOfRows.add(firstRow);
            listOfRows.add(secondRow);

            replyKeyboard.setKeyboard(listOfRows);
            sndMessage.setReplyMarkup(replyKeyboard);

            UniScheduleBot.getInstance().sendMessage(sndMessage);
        }catch (SQLException e){
            //TODO HANDLE THIS
            e.printStackTrace();
            logger.error("Couldn't change subgroup of user" + passedUpdate.getCallbackQuery().getMessage().getChatId(), e);
            SendMessage snd = new SendMessage()
                    .setChatId(passedUpdate.getCallbackQuery().getMessage().getChatId())
                    .setText(localizationBundle.getString(LocalizationField.ERROR_DB_RETRIEVE_SUBGROUP));
            UniScheduleBot.getInstance().sendMessage(snd);
        }
    }
}