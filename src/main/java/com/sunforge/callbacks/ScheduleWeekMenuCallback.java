package com.sunforge.callbacks;

import com.sunforge.UniScheduleBot;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ScheduleWeekMenuCallback {
    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();
    private static final Logger logger = LogManager.getLogger(ScheduleWeekMenuCallback.class);

    static void changeKeyboardToDays(Update passedUpdate) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(passedUpdate.getCallbackQuery().getMessage().getChatId())
        .setMessageId(passedUpdate.getCallbackQuery().getMessage().getMessageId())
        .setText(localizationBundle.getString(LocalizationField.SCHEDULE_RESPONSE));

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        //Keyboard with 4 days:
        //Mon Tues Wed Fri
        List<InlineKeyboardButton> daysRow = new ArrayList<>();
        daysRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.MONDAY_SHORTENED)).setCallbackData("schedule_week_monday"));
        daysRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.TUESDAY_SHORTENED)).setCallbackData("schedule_week_tuesday"));
        daysRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.WEDNESDAY_SHORTENED)).setCallbackData("schedule_week_wednesday"));
        daysRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.FRIDAY_SHORTENED)).setCallbackData("schedule_week_friday"));

        //Adding Back button
        List<InlineKeyboardButton> backButtonRow = new ArrayList<>();
        backButtonRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.BACK)).setCallbackData("schedule_menu"));

        rows.add(daysRow);
        rows.add(backButtonRow);
        inlineKeyboardMarkup.setKeyboard(rows);
        editMessageText.setReplyMarkup(inlineKeyboardMarkup);
        logger.debug("Create markup for edited message in schedule_week for " + passedUpdate.getCallbackQuery().getMessage().getChatId());

        UniScheduleBot.getInstance().editMessageText(editMessageText);
    }
}