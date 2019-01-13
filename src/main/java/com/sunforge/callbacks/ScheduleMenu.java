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

public class ScheduleMenu {
    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();
    private static final Logger logger = LogManager.getLogger(ScheduleMenu.class);

    protected static void sendScheduleMenuKeyboard(Update passedUpdate) {
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

        UniScheduleBot.getInstance().editMessageText(edit);
    }

    protected static void addWeekBack(Update passedUpdate) {
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

        UniScheduleBot.getInstance().editReplyMarkupMessage(edit);
    }

    protected static void addNextWeekBack(Update passedUpdate) {
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

        UniScheduleBot.getInstance().editReplyMarkupMessage(edit);
    }
}
