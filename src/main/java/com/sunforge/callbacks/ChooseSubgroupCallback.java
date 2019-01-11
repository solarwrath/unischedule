package com.sunforge.callbacks;

import com.sunforge.db.UserOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.SQLException;

public class ChooseSubgroupCallback {

    private static final Logger logger = LogManager.getLogger(ChooseSubgroupCallback.class);

    public static void chooseSubgroupCallback(Update passedUpdate, int chosenSubgroup){
        try {
            UserOperations.changeSubgroup(passedUpdate.getCallbackQuery().getFrom().getId(), chosenSubgroup);

            //TODO Indicate THE CHANGE
        }catch (SQLException e){
            //TODO HANDLE THIS
            e.printStackTrace();
        }
    }
}
