package com.sunforge.callbacks;

import com.sunforge.commands.UnknownCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.sunforge.callbacks.ChooseSubgroupCallback.chooseSubgroupCallback;

public class CallBackDistributionHandler {

    private static final Logger logger = LogManager.getLogger(CallBackDistributionHandler.class);

    public static void handleCallBack(Update passedUpdate) {
        String call_data = passedUpdate.getCallbackQuery().getData();

        switch (call_data) {
            case "choose_subgroup_first":
                logger.debug("Got choose first subgroup callback query");
                chooseSubgroupCallback(passedUpdate, 1);
                break;
            case "choose_subgroup_second":
                logger.debug("Got choose second subgroup callback query");
                chooseSubgroupCallback(passedUpdate, 2);
                break;
            case "settings_back":
                logger.debug("Got settings_back callback query");
                SettingsBackCallback.settingsBack(passedUpdate);
                break;
            default:
                logger.error("Got unknown callback_query: " + call_data);
                //TODO HANDLE THIS
                UnknownCommand.sendUnknownCommandMessage(passedUpdate);
        }
    }
}
