package com.sunforge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.sunforge.ValidationHandler.validateCommand;
import static com.sunforge.callbacks.CallBackDistributionHandler.handleCallBack;
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
        if(update.hasMessage() && update.getMessage().hasText()){
            String userMessage = update.getMessage().getText();
            logger.info("Message from " + update.getMessage().getFrom().getUserName() + ": \"" + userMessage+"\"");

            if(validateCommand(userMessage)){
                logger.trace("Validated command " +userMessage);
                handleCommand(update);
            }

        }else if (update.hasCallbackQuery()) {
            handleCallBack(update);
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

    public void sendPassedMessage(SendMessage passedMessage){
        try{
            execute(passedMessage);
            logger.debug("Sended a message to user " + passedMessage.getChatId()+ " \"" + passedMessage.getText() + "\"");
        } catch (TelegramApiException e) {
            logger.error("Telegram stuff fucked up");
            e.printStackTrace();
        }
    }
    public void deleteMessage(DeleteMessage passedMessage){
        try{
            execute(passedMessage);
            logger.debug("Deleted message in the dialog with user " + passedMessage.getChatId()+ ". Message id: " + passedMessage.getMessageId());
        } catch (TelegramApiException e) {
            logger.error("Telegram stuff fucked up");
            e.printStackTrace();
        }
    }



}
