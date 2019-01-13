package com.sunforge.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperations {

    private static final Logger logger = LogManager.getLogger(UserOperations.class);

    public static void initializeUser(long chatId, String username, String firstName, String lastName) throws SQLException{
        final String query = "REPLACE INTO users (chat_id, username, first_name, last_name)" +
                " VALUES ("+chatId+", '" +username + "', '" + firstName + "', '" + lastName + "')";
        logger.debug("Trying to execute: " + query);

        try(Connection con = DataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(query)
        ){
            pst.execute();
            logger.debug("Successfully inserted new user: [" + chatId + "," + username + "," + firstName + "," + lastName+"]");
        }
    }

    public static void changeSubgroup(long chatId, int chosenSubgroup) throws SQLException{
        final String query = "UPDATE users SET subgroup=" + chosenSubgroup + " WHERE chat_id="+chatId;
        logger.debug("Trying to execute: " + query);

        try(Connection con = DataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(query)
        ){
            pst.execute();
            logger.debug("Successfully changed subgroup of user " + chatId +" to " + chosenSubgroup);
        }
    }

    public static short getSubgroup(long chatId) throws SQLException{
        final String query = "SELECT subgroup FROM users WHERE chat_id=" + chatId;
        logger.debug("Trying to execute: " + query);

        try(Connection con = DataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery()
        ){
            rs.next();
            short redeemedSubgroup = rs.getShort("subgroup");
            logger.debug("Successfully redeemed subgroup of user " + chatId +": " + redeemedSubgroup);

            return redeemedSubgroup;
        }
    }
}