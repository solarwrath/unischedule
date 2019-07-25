package com.sunforge;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

public class App{
    public static void main( String[] args )
    {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            LongPollingBot singletonBot = UniScheduleBot.getInstance();
            telegramBotsApi.registerBot(singletonBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
