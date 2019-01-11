package com.sunforge.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandDistributionHandler {

    private static final Logger logger = LogManager.getLogger(CommandDistributionHandler.class);

    public static void handleCommand(Update passedUpdate){
        String givenCommand = passedUpdate.getMessage().getText();

        switch(givenCommand){
            case "/start":
                logger.info("Got /start command");
                StartCommand.handleStartCommand(passedUpdate);
                break;
            case "/daySchedule":
                logger.info("Got /daySchedule command");
                DayScheduleCommand.sendDayScheduleMessage(passedUpdate);
                break;
            case "/settings":
                logger.info("Got /settings command");
                SettingsCommand.sendSettingsKeyboard(passedUpdate);
                break;
            default:
                logger.warn("Can't interpret passed command");
                UnknownCommand.sendUnknownCommandMessage(passedUpdate);
        }
    }
}
