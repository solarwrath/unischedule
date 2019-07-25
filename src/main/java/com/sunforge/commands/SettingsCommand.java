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
    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

    static void sendSettingsKeyboard(Update passedUpdate) {
        SendMessage snd = new SendMessage()
                .setChatId(passedUpdate.getMessage().getChatId())
                .setText(localizationBundle.getString(LocalizationField.SETTINGS));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.FIRST_SUBGROUP)).setCallbackData("choose_subgroup_first"));
        firstRow.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.SECOND_SUBGROUP)).setCallbackData("choose_subgroup_second"));

        rowsInline.add(firstRow);

        markupInline.setKeyboard(rowsInline);
        snd.setReplyMarkup(markupInline);

        logger.debug("Sending SettingsMessage", snd);

        UniScheduleBot.getInstance().sendMessage(snd);
    }
}
