package com.sunforge.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;

public class ScheduleReader {

    private static final Logger logger = LogManager.getLogger(ScheduleReader.class);

    public static String getScheduleFromDay(DayOfWeek givenDay, boolean isEvenWeek, int givenSubgroup) throws IllegalArgumentException, SQLException {
        boolean interpretError = false;
        String interpretedDay = "";
        switch (givenDay) {
            case MONDAY: {
                interpretedDay = "monday";
                break;
            }
            case TUESDAY: {
                interpretedDay = "tuesday";
                break;
            }
            case WEDNESDAY: {
                interpretedDay = "wednesday";
                break;
            }
            case THURSDAY: {
                interpretedDay = "thursday";
                break;
            }
            case FRIDAY: {
                interpretedDay = "friday";
                break;
            }
            default:
                interpretError = true;
        }

        if (interpretError) {
            logger.error("Couldn't resolve day: " + givenDay);
            throw new IllegalArgumentException("Illegal day passed to interpret!");
        }

        logger.debug("Successfuly interpreted day: " + interpretedDay);

        final String queryToSelectSchedule = "SELECT * FROM " + interpretedDay + "_" + givenSubgroup;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(queryToSelectSchedule);
             ResultSet rs = pst.executeQuery();
        ) {
            logger.debug("Opened all the db stuff connection");
            StringBuilder stringBuilder = new StringBuilder("----------------------------------------------------------------------\n");
            if(isEvenWeek){
                while (rs.next()) {
                    int currentOddity = rs.getInt("oddity");
                    if(currentOddity == 2 || currentOddity == 3){
                        //stringBuilder.append("scheduleOrder:").append(rs.getInt("scheduleOrder")).append("\n");
                        stringBuilder.append("Дисципліна: ").append(rs.getString("subject")).append("\n");
                        stringBuilder.append("Аудиторія: ").append(rs.getString("class")).append("\n");
                        stringBuilder.append("Викладач: ").append(rs.getString("teacher")).append("\n");
                        stringBuilder.append("----------------------------------------------------------------------\n");
                    }
                }
            }else{
                while (rs.next()) {
                    int currentOddity = rs.getInt("oddity");
                    if(currentOddity == 1 || currentOddity == 3){
                        //stringBuilder.append("scheduleOrder:").append(rs.getInt("scheduleOrder")).append("\n");
                        stringBuilder.append("Дисципліна: ").append(rs.getString("subject")).append("\n");
                        stringBuilder.append("Аудиторія: ").append(rs.getString("class")).append("\n");
                        stringBuilder.append("Викладач: ").append(rs.getString("teacher")).append("\n");
                        stringBuilder.append("----------------------------------------------------------------------\n");
                    }
                }
            }

            logger.debug("Created a schedule from query: " + queryToSelectSchedule);

            return stringBuilder.toString();

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Something went bad with db stuff. Query: " + queryToSelectSchedule);
            throw new SQLException("Something went bad with db stuff. Query: " + queryToSelectSchedule);
        }
    }
}
