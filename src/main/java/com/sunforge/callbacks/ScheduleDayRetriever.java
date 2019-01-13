package com.sunforge.callbacks;

import com.sunforge.UniScheduleBot;
import com.sunforge.db.ScheduleReader;
import com.sunforge.db.UserOperations;
import com.sunforge.properties.LocalizationBundle;
import com.sunforge.properties.LocalizationField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.Calendar;

import static com.sunforge.callbacks.CommonMethods.editMessageToScheduleMenu;
import static com.sunforge.callbacks.ScheduleMenu.addNextWeekBack;
import static com.sunforge.callbacks.ScheduleMenu.addWeekBack;

public class ScheduleDayRetriever {

    private static final Logger logger = LogManager.getLogger(ScheduleDayRetriever.class);
    private static final LocalizationBundle localizationBundle = LocalizationBundle.getInstance();

    private static void sendDayScheduleMessage(Update passedUpdate, int currentDay, boolean isEvenWeek) {
        EditMessageText editText = new EditMessageText().
                setChatId(passedUpdate.getCallbackQuery().getMessage().getChatId()).
                setMessageId(passedUpdate.getCallbackQuery().getMessage().getMessageId());

        String textToSend;
        try {
            short userSubgroup = UserOperations.getSubgroup(passedUpdate.getCallbackQuery().getMessage().getChatId());
            try {
                switch (currentDay) {
                    case 2:
                        textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.MONDAY, isEvenWeek, userSubgroup);
                        break;
                    case 3:
                        textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.TUESDAY, isEvenWeek, userSubgroup);
                        break;
                    case 4:
                        textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.WEDNESDAY, isEvenWeek, userSubgroup);
                        break;
                    case 6:
                        textToSend = ScheduleReader.getScheduleFromDay(DayOfWeek.FRIDAY, isEvenWeek, userSubgroup);
                        break;

                    //If Thursday, Saturday or Sunday, display that it is a day off
                    default:
                        textToSend = localizationBundle.getString(LocalizationField.DAY_OFF);
                        break;
                }
            } catch (SQLException e) {
                logger.error("Couldn't get the schedule from db. Current day: " + currentDay, e);
                textToSend = localizationBundle.getString(LocalizationField.ERROR_DB_RETRIEVE_SCHEDULE);
            }
        } catch (SQLException e) {
            logger.error("Can't get user subgroup. User: " + passedUpdate.getCallbackQuery().getMessage().getChatId(), e);
            textToSend = localizationBundle.getString(LocalizationField.ERROR_DB_RETRIEVE_SUBGROUP);
        }

        editText.setText(textToSend);

        UniScheduleBot.getInstance().editMessageText(editText);
    }


    static void sendTodaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        editMessageToScheduleMenu(passedUpdate);
    }

    static void sendTomorrowSchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        editMessageToScheduleMenu(passedUpdate);
    }

    static void sendMondaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.MONDAY;
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addWeekBack(passedUpdate);
    }

    static void sendTuesdaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.TUESDAY;
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addWeekBack(passedUpdate);
    }

    static void sendWednesdaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.WEDNESDAY;
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addWeekBack(passedUpdate);
    }

    static void sendThursdaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.THURSDAY;
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addWeekBack(passedUpdate);
    }

    static void sendFridaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.FRIDAY;
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addWeekBack(passedUpdate);
    }

    static void sendSaturdaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.SATURDAY;
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addWeekBack(passedUpdate);
    }

    static void sendSundaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.SUNDAY;
        boolean isEvenWeek = (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addWeekBack(passedUpdate);
    }

    static void sendNextMondaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.MONDAY;
        boolean isEvenWeek = !(calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addNextWeekBack(passedUpdate);
    }

    static void sendNextTuesdaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.TUESDAY;
        boolean isEvenWeek = !(calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addNextWeekBack(passedUpdate);
    }

    static void sendNextWednesdaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.WEDNESDAY;
        boolean isEvenWeek = !(calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addNextWeekBack(passedUpdate);
    }

    public static void sendNextThursdaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.THURSDAY;
        boolean isEvenWeek = !(calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addNextWeekBack(passedUpdate);
    }

    static void sendNextFridaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.FRIDAY;
        boolean isEvenWeek = !(calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addNextWeekBack(passedUpdate);
    }

    static void sendNextSaturdaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.SATURDAY;
        boolean isEvenWeek = !(calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addNextWeekBack(passedUpdate);
    }

    static void sendNextSundaySchedule(Update passedUpdate) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = Calendar.SUNDAY;
        boolean isEvenWeek = !(calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0);

        sendDayScheduleMessage(passedUpdate, currentDay, isEvenWeek);
        addNextWeekBack(passedUpdate);
    }
}