package com.sunforge.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperations {

    private static final Logger logger = LogManager.getLogger(UserOperations.class);

    public static void initializeUser(long telegramId, String username, String firstName, String lastName) throws SQLException{
        final String query = "REPLACE INTO users (telegram_id, username, first_name, last_name)" +
                " VALUES ("+telegramId+", '" +username + "', '" + firstName + "', '" + lastName + "')";
        logger.debug("Trying to execute: " + query);

        try(Connection con = DataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(query)
        ){
            pst.execute();
            logger.debug("Successfully inserted new user: [" + telegramId + "," + username + "," + firstName + "," + lastName+"]");
        }
    }

    public static void changeSubgroup(long telegramId, int chosenSubgroup) throws SQLException{
        final String query = "UPDATE users SET subgroup=" + chosenSubgroup + " WHERE telegram_id="+telegramId;
        logger.debug("Trying to execute: " + query);

        try(Connection con = DataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(query)
        ){
            pst.execute();
            logger.debug("Successfully changed subgroup of user " + telegramId +" to " + chosenSubgroup);
        }
    }

    public static short getSubgroup(long telegramId) throws SQLException{
        final String query = "SELECT subgroup FROM users WHERE telegram_id=" + telegramId;
        logger.debug("Trying to execute: " + query);

        try(Connection con = DataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery()
        ){
            rs.next();
            short redeemedSubgroup = rs.getShort("subgroup");
            logger.debug("Successfully redeemed subgroup of user " + telegramId +": " + redeemedSubgroup);

            return redeemedSubgroup;
        }
    }
}
