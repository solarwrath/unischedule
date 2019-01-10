package com.sunforge.commands;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandDistributionHandler {

    public static void handleCommand(Update passedUpdate){
        String givenCommand = passedUpdate.getMessage().getText();
        switch(givenCommand){
            case "/start":
                break;
            case "/daySchedule":
                DayScheduleCommand.sendDayScheduleMessage(passedUpdate);
                break;
            default:
                UnknownCommand.sendUnknownCommandMessage(passedUpdate);
        }
    }
}
