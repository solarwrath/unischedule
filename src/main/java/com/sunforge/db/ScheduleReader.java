package com.sunforge.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;

public class ScheduleReader {
    public static String getScheduleFromDay(DayOfWeek givenDay) throws IllegalArgumentException{
        String result = "Error";
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

        if(interpretError){
            throw new IllegalArgumentException("Доступны только дни с понедельника по пятницу!");
        }

        final String queryToSelectSchedule = "SELECT * FROM " + interpretedDay + "_1";
        try(Connection con = DataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(queryToSelectSchedule);
            ResultSet rs = pst.executeQuery();){

            StringBuilder stringBuilder = new StringBuilder();
            while(rs.next()){
                stringBuilder.append("scheduleOrder:").append(rs.getInt("scheduleOrder")).append("\n");
                stringBuilder.append("oddity:").append(rs.getInt("oddity")).append("\n");
                stringBuilder.append("subject:").append(rs.getString("subject")).append("\n");
                stringBuilder.append("teacher:").append(rs.getString("teacher")).append("\n");
                stringBuilder.append("class:").append(rs.getString("class")).append(";");
            }

            result = stringBuilder.toString();

        }catch (SQLException e){
                e.printStackTrace();
                result = "Error";
        }

        return result;
    }
}
