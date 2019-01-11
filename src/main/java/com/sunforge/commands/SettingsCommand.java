package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class SettingsCommand {

    private static final Logger logger = LogManager.getLogger(SettingsCommand.class);

    protected static void sendSettingsKeyboard(Update passedUpdate) {
        SendMessage snd = new SendMessage() // Create a message object object
                .setChatId(passedUpdate.getMessage().getChatId())
                .setText("Налаштування");

        logger.debug("Created SendMessage", snd);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(new InlineKeyboardButton().setText("I підгруппа").setCallbackData("settings_choose_first_subgroup"));
        firstRow.add(new InlineKeyboardButton().setText("II підгруппа").setCallbackData("settings_choose_second_subgroup"));

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        secondRow.add(new InlineKeyboardButton().setText("Назад").setCallbackData("settings_back"));

        rowsInline.add(firstRow);
        rowsInline.add(secondRow);

        markupInline.setKeyboard(rowsInline);
        snd.setReplyMarkup(markupInline);

        logger.debug("Successfully set ReplyMarkup", markupInline);

        UniScheduleBot.getInstance().sendPassedMessage(snd);
    }
}
