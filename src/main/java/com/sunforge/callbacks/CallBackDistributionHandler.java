package com.sunforge.callbacks;

import com.sunforge.commands.UnknownCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.sunforge.callbacks.StartCallback.startCallback;

public class CallBackDistributionHandler {

    private static final Logger logger = LogManager.getLogger(CallBackDistributionHandler.class);

    public static void handleCallBack(Update passedUpdate) {
        String call_data = passedUpdate.getCallbackQuery().getData();

        switch (call_data) {
            case "start_subgroup_first":
                logger.debug("Got start_subgroup_first callback query");
                startCallback(passedUpdate, 1);
                break;
            case "start_subgroup_second":
                logger.debug("Got start_subgroup_second callback query");
                startCallback(passedUpdate, 2);
                break;
            case "schedule_menu":
                logger.debug("Got schedule_menu callback query");
                ScheduleMenu.sendScheduleMenuKeyboard(passedUpdate);
                break;
            case "schedule_today":
                logger.debug("Got schedule_today callback query");
                ScheduleDayRetriever.sendTodaySchedule(passedUpdate);
                break;
            case "schedule_tomorrow":
                logger.debug("Got schedule_tomorrow callback query");
                ScheduleDayRetriever.sendTomorrowSchedule(passedUpdate);
                break;
            case "schedule_week":
                logger.debug("Got schedule_week callback query");
                ScheduleWeekMenuCallback.changeKeyboardToDays(passedUpdate);
                break;
            case "schedule_week_monday":
                logger.debug("Got schedule_week_monday callback query");
                ScheduleDayRetriever.sendMondaySchedule(passedUpdate);
                break;
            case "schedule_week_tuesday":
                logger.debug("Got schedule_week_tuesday callback query");
                ScheduleDayRetriever.sendTuesdaySchedule(passedUpdate);
                break;
            case "schedule_week_wednesday":
                logger.debug("Got schedule_week_wednesday callback query");
                ScheduleDayRetriever.sendWednesdaySchedule(passedUpdate);
                break;
            case "schedule_week_friday":
                logger.debug("Got schedule_week_friday callback query");
                ScheduleDayRetriever.sendFridaySchedule(passedUpdate);
                break;
            case "schedule_next_week":
                logger.debug("Got schedule_next_week callback query");
                ScheduleNextWeekMenuCallback.changeKeyboardToDays(passedUpdate);
                break;
            case "schedule_next_week_monday":
                logger.debug("Got schedule_next_week_monday callback query");
                ScheduleDayRetriever.sendNextMondaySchedule(passedUpdate);
                break;
            case "schedule_next_week_tuesday":
                logger.debug("Got schedule_next_week_tuesday callback query");
                ScheduleDayRetriever.sendNextTuesdaySchedule(passedUpdate);
                break;
            case "schedule_next_week_wednesday":
                logger.debug("Got schedule_next_week_wednesday callback query");
                ScheduleDayRetriever.sendNextWednesdaySchedule(passedUpdate);
                break;
            case "schedule_next_week_friday":
                logger.debug("Got schedule_next_week_friday callback query");
                ScheduleDayRetriever.sendNextFridaySchedule(passedUpdate);
                break;
            case "choose_subgroup_first":
                logger.debug("Got choose_subgroup_second callback query");
                ChangeSubgroupCallback.changeSubgroupCallback(passedUpdate, 1);
                break;
            case "choose_subgroup_second":
                logger.debug("Got choose_subgroup_second callback query");
                ChangeSubgroupCallback.changeSubgroupCallback(passedUpdate, 2);
                break;
            default:
                logger.error("Got unknown callback_query: " + call_data);
                //TODO HANDLE THIS
                UnknownCommand.sendUnknownCommandMessage(passedUpdate);
        }
    }
}
