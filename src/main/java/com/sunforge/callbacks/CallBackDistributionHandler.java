package com.sunforge.callbacks;

import com.sunforge.commands.UnknownCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CallBackDistributionHandler {

    private static final Logger logger = LogManager.getLogger(CallBackDistributionHandler.class);

    public static void handleCallBack(Update passedUpdate) {
        String call_data = passedUpdate.getCallbackQuery().getData();

        switch (call_data) {
            case "settings_back":
                logger.debug("Got settings_back callback query");
                SettingsBackCallback.settingsBack(passedUpdate);
                break;
            default:
                logger.error("Got unknown callback_query", passedUpdate);
                UnknownCommand.sendUnknownCommandMessage(passedUpdate);
        }
    }
}
