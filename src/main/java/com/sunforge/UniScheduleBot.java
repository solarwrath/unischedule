package com.sunforge;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.sunforge.ValidationHandler.validateCommand;
import static com.sunforge.commands.CommandDistributionHandler.handleCommand;

public class UniScheduleBot extends TelegramLongPollingBot{

    private UniScheduleBot(){}

    private static UniScheduleBot instance;

    public static UniScheduleBot getInstance(){
        if(instance == null){
            instance = new UniScheduleBot();
        }
        return instance;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String userMessage = update.getMessage().getText().toLowerCase();
        if(validateCommand(userMessage)){
            handleCommand(update);
        }
    }

    @Override
    public String getBotUsername() {
        return "unischedule_bot";
    }

    @Override
    public String getBotToken() {
        return "773829235:AAGbh1wRiBnfYhJjMCcFHT--2GL-0jzixWk";
    }



}
