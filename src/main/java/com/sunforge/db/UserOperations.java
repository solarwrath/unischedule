package com.sunforge.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserOperations {

    private static final Logger logger = LogManager.getLogger(UserOperations.class);

    public static void initializeUser(long telegramId, String username, String firstName, String lastName) throws SQLException{
        final String queryToInitializeUser = "INSERT INTO users (telegram_id, username, first_name, last_name)" +
                " VALUES ("+telegramId+", '" +username + "', '" + firstName + "', '" + lastName + "')";
        logger.trace("Trying to execute: " + queryToInitializeUser);

        try(Connection con = DataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(queryToInitializeUser);
        ){
            pst.execute();
            logger.debug("Successfully inserted new user: [" + telegramId + "," + username + "," + firstName + "," + lastName);
        }
    }
}
