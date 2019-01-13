package com.sunforge.commands;

import com.sunforge.UniScheduleBot;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sunforge.db.UserOperations.initializeUser;

public class StartCommand {

    private static final Logger logger = LogManager.getLogger(StartCommand.class);

    public static void handleStartCommand(Update passedUpdate){
        long chat_id = passedUpdate.getMessage().getChatId();
        Message passedMessage = passedUpdate.getMessage();
        User currentUser = passedMessage.getFrom();

        String username = currentUser.getUserName();
        String firstName = currentUser.getFirstName();
        String lastName = currentUser.getLastName();

        try {
            //Create a row in table 'users' in db and populate it with user's id, name and username
            initializeUser(chat_id, username, firstName, lastName);

            //Get greetings message

            LocalizationBundle localizationBundle = LocalizationBundle.getInstance();
            String greetingsMessageText = localizationBundle.getString(LocalizationField.START_CHOOSE_SUBGROUP);

            SendMessage startChooseSubgroupMessage = new SendMessage().setChatId(chat_id).setText(greetingsMessageText);

            //Create inlineKeyboard with 2 buttons with subgroups
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

            List<List<InlineKeyboardButton>> rows = new ArrayList<>();
            List<InlineKeyboardButton> row = new ArrayList<>();

            row.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.FIRST_SUBGROUP)).setCallbackData("start_subgroup_first"));
            row.add(new InlineKeyboardButton().setText(localizationBundle.getString(LocalizationField.SECOND_SUBGROUP)).setCallbackData("start_subgroup_second"));

            rows.add(row);

            keyboardMarkup.setKeyboard(rows);
            startChooseSubgroupMessage.setReplyMarkup(keyboardMarkup);

            UniScheduleBot.getInstance().sendMessage(startChooseSubgroupMessage);
        } catch (SQLException e) {
            logger.error("Couldn't initialize user in db", e);
        }
    }
}
