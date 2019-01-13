package com.sunforge.callbacks;

import com.sunforge.UniScheduleBot;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CommonMethods {
    private static final Logger logger = LogManager.getLogger(CommonMethods.class);
    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

    static void editMessageToScheduleMenu(Update passedUpdate){

        EditMessageReplyMarkup editKeyboard = new EditMessageReplyMarkup()
                .setChatId(passedUpdate.getCallbackQuery().getMessage().getChatId())
                .setMessageId(passedUpdate.getCallbackQuery().getMessage().getMessageId());

        logger.debug("Created EditMessageReplyMarkup for ScheduleMenu");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.BACK)).setCallbackData("schedule_menu"));

        rows.add(row);
        keyboardMarkup.setKeyboard(rows);

        editKeyboard.setReplyMarkup(keyboardMarkup);

        logger.debug("Populated the keyboard with buttons");

        UniScheduleBot.getInstance().editReplyMarkupMessage(editKeyboard);
    }

}