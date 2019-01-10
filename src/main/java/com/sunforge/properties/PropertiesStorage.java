package com.sunforge.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesStorage {

    private static String rootPath;
    private static final String STANDARD_MESSAGES_RELATIVE_PATH = "standardMessages.properties";
    private static final String DATABASE_AUTHENTICATION_RELATIVE_PATH = "databaseAuthentication.properties";

    static {
        try{
            rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "properties/";
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    //Standard Messages

    private static Properties standardMessages;

    public static Properties getStandardMessages() throws IOException {
        if(standardMessages == null){
            standardMessages = new Properties();
            FileInputStream input = new FileInputStream(rootPath+STANDARD_MESSAGES_RELATIVE_PATH);
            standardMessages.load(new InputStreamReader(input, Charset.forName("UTF-8")));
        }
        return standardMessages;
    }

    //Database Authentication

    private static Properties databaseAuthentication;

    public static Properties getDatabaseAuthentication() throws IOException {
        if(databaseAuthentication == null){
            databaseAuthentication = new Properties();
            FileInputStream input = new FileInputStream(rootPath+DATABASE_AUTHENTICATION_RELATIVE_PATH);
            databaseAuthentication.load(new InputStreamReader(input, Charset.forName("UTF-8")));
        }
        return databaseAuthentication;
    }
}
