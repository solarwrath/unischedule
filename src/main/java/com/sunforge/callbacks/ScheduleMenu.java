package com.sunforge.callbacks;

import com.sunforge.UniScheduleBot;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

class ScheduleMenu {
    private static final Logger logger = LogManager.getLogger(ScheduleMenu.class);
    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

    static void sendScheduleMenuKeyboard(Update passedUpdate) {
        EditMessageText edit = new EditMessageText()
                .setChatId(passedUpdate.getCallbackQuery().getMessage().getChatId())
                .setMessageId(passedUpdate.getCallbackQuery().getMessage().getMessageId())
                .setText(localizationBundle.getString(LocalizationField.SCHEDULE_RESPONSE));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.TODAY)).setCallbackData("schedule_today"));
        firstRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.TOMORROW)).setCallbackData("schedule_tomorrow"));

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        secondRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.WEEK)).setCallbackData("schedule_week"));

        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        thirdRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.NEXT_WEEK)).setCallbackData("schedule_next_week"));

        rowsInline.add(firstRow);
        rowsInline.add(secondRow);
        rowsInline.add(thirdRow);

        markupInline.setKeyboard(rowsInline);
        edit.setReplyMarkup(markupInline);

        logger.debug("Editing message to ScheduleMenu");
        UniScheduleBot.getInstance().editMessageText(edit);
    }

    static void addWeekBack(Update passedUpdate) {
        EditMessageReplyMarkup edit = new EditMessageReplyMarkup()
                .setChatId(passedUpdate.getCallbackQuery().getMessage().getChatId())
                .setMessageId(passedUpdate.getCallbackQuery().getMessage().getMessageId());

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.BACK)).setCallbackData("schedule_week"));

        rowsInline.add(row);

        markupInline.setKeyboard(rowsInline);
        edit.setReplyMarkup(markupInline);

        logger.debug("Adding Back button to schedule_week");
        UniScheduleBot.getInstance().editReplyMarkupMessage(edit);
    }

    static void addNextWeekBack(Update passedUpdate) {
        EditMessageReplyMarkup edit = new EditMessageReplyMarkup()
                .setChatId(passedUpdate.getCallbackQuery().getMessage().getChatId())
                .setMessageId(passedUpdate.getCallbackQuery().getMessage().getMessageId());

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.BACK)).setCallbackData("schedule_next_week"));

        rowsInline.add(row);

        markupInline.setKeyboard(rowsInline);
        edit.setReplyMarkup(markupInline);

        logger.debug("Adding Back button to schedule_next_week");
        UniScheduleBot.getInstance().editReplyMarkupMessage(edit);
    }
}