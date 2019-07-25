package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ScheduleCommand {
    private static final Logger logger = LogManager.getLogger(ScheduleCommand.class);
    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

    static void sendScheduleMarkup(Update passedUpdate){

        SendMessage snd = new SendMessage()
                .setChatId(passedUpdate.getMessage().getChatId())
                .setText(localizationBundle.getString(LocalizationField.SCHEDULE_RESPONSE));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.TODAY)).setCallbackData("schedule_today"));
        firstRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.TOMORROW)).setCallbackData("schedule_tomorrow"));

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        secondRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.WEEK)).setCallbackData("schedule_week"));;

        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        thirdRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.NEXT_WEEK)).setCallbackData("schedule_next_week"));

        rowsInline.add(firstRow);
        rowsInline.add(secondRow);
        rowsInline.add(thirdRow);

        markupInline.setKeyboard(rowsInline);
        snd.setReplyMarkup(markupInline);

        logger.debug("Sending schedule menu message");

        UniScheduleBot.getInstance().sendMessage(snd);
    }
}