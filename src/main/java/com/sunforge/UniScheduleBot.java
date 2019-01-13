package com.sunforge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.sunforge.ValidationHandler.validateCommand;
import static com.sunforge.callbacks.CallBackDistributionHandler.handleCallBack;
import static com.sunforge.commands.CommandDistributionHandler.handleCommand;

public class UniScheduleBot extends TelegramLongPollingBot{

    private static final Logger logger = LogManager.getLogger(UniScheduleBot.class);

    private UniScheduleBot(){}

    private static UniScheduleBot instance;

    public static UniScheduleBot getInstance(){
        if(instance == null){
            instance = new UniScheduleBot();
            logger.debug("Created UniScheduleBot instance", instance);
        }
        return instance;
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.debug("Got update from " + ((update.hasMessage()) ? update.getMessage().getChatId() : update.getCallbackQuery().getMessage().getChatId()), update);

        //If update is non-null message from user (both custom and ReplyKeyboard's messages
        if(update.hasMessage() && update.getMessage().hasText()){
            String userMessage = update.getMessage().getText();
            logger.info("Command from " + update.getMessage().getChatId() + ": \"" + userMessage+"\"");

            if(validateCommand(userMessage)){
                logger.debug("Validated command " + userMessage);
                handleCommand(update);
            }else{
                logger.debug("The command I got wasn't valid: " + userMessage);
            }
        //If update is callback query
        }else if (update.hasCallbackQuery()) {
            logger.info("Callback query from " + update.getCallbackQuery().getMessage().getChatId() + ": \"" + update.getCallbackQuery().getData()+"\"");
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

    public void sendMessage(SendMessage passedMessage){
        try{
            execute(passedMessage);
            logger.debug("Sent a message to user " + passedMessage.getChatId()+ " \"" + passedMessage.getText() + "\"");
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

    public void editMessageText(EditMessageText passedMessage){
        try{
            execute(passedMessage);
            logger.debug("Edited message text in the dialog with user " + passedMessage.getChatId()+ ". Message id: " + passedMessage.getMessageId());
        } catch (TelegramApiException e) {
            logger.error("Telegram stuff fucked up");
            e.printStackTrace();
        }
    }

    public void editReplyMarkupMessage(EditMessageReplyMarkup passedMessage){
        try{
            execute(passedMessage);
            logger.debug("Edited message reply markup in the dialog with user " + passedMessage.getChatId()+ ". Message id: " + passedMessage.getMessageId());
        } catch (TelegramApiException e) {
            logger.error("Telegram stuff fucked up");
            e.printStackTrace();
        }
    }
}
