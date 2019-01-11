package com.sunforge;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger logger = LogManager.getLogger(UniScheduleBot.class);

    @Override
    public void onUpdateReceived(Update update) {
        String userMessage = update.getMessage().getText();
        logger.info("Message from " + update.getMessage().getFrom().getUserName() + ": \"" + userMessage+"\"");
        if(validateCommand(userMessage)){
            logger.trace("Validated command " +userMessage);
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
