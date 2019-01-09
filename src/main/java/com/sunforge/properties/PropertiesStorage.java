package com.sunforge.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesStorage {

    private static String rootPath;
    private static String standardMessagesRelativePath = "standardMessages.properties";

    static {
        try{
            rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "properties/";
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    private static Properties standardMessages;

    public static Properties getStandardMessages() throws IOException {
        if(standardMessages == null){
            standardMessages = new Properties();
            FileInputStream input = new FileInputStream(rootPath+standardMessagesRelativePath);
            standardMessages.load(new InputStreamReader(input, Charset.forName("UTF-8")));
        }
        return standardMessages;
    }
}
