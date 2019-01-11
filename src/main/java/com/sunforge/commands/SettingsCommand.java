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

public class SettingsCommand {

    private static final Logger logger = LogManager.getLogger(SettingsCommand.class);

    protected static void sendSettingsKeyboard(Update passedUpdate) {
        LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

        SendMessage snd = new SendMessage()
                .setChatId(passedUpdate.getMessage().getChatId())
                .setText(localizationBundle.getString(LocalizationField.SETTINGS));

        logger.debug("Created SendMessage", snd);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.FIRST_SUBGROUP)).setCallbackData("choose_subgroup_first"));
        firstRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.SECOND_SUBGROUP)).setCallbackData("choose_subgroup_second"));

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        secondRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.BACK)).setCallbackData("settings_back"));

        rowsInline.add(firstRow);
        rowsInline.add(secondRow);

        markupInline.setKeyboard(rowsInline);
        snd.setReplyMarkup(markupInline);

        logger.debug("Successfully set ReplyMarkup", markupInline);

        UniScheduleBot.getInstance().sendPassedMessage(snd);
    }
}
