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

public class ScheduleWeekMenuCallback {
    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();
    private static final Logger logger = LogManager.getLogger(ScheduleWeekMenuCallback.class);

    public static void changeKeyboardToDays(Update passedUpdate) {
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setChatId(passedUpdate.getCallbackQuery().getMessage().getChatId())
        .setMessageId(passedUpdate.getCallbackQuery().getMessage().getMessageId());

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.MONDAY_SHORTENED)).setCallbackData("schedule_week_monday"));
        row.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.TUESDAY_SHORTENED)).setCallbackData("schedule_week_tuesday"));
        row.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.WEDNESDAY_SHORTENED)).setCallbackData("schedule_week_wednesday"));
        row.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.FRIDAY_SHORTENED)).setCallbackData("schedule_week_friday"));

        rows.add(row);
        inlineKeyboardMarkup.setKeyboard(rows);
        editMessageReplyMarkup.setReplyMarkup(inlineKeyboardMarkup);
        logger.debug("Create markup for edited message in schedule_week for " + passedUpdate.getCallbackQuery().getMessage().getFrom().getId());

        UniScheduleBot.getInstance().editReplyMarkupMessage(editMessageReplyMarkup);
    }
}
