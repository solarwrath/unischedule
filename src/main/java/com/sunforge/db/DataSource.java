package com.sunforge.db;

import com.sunforge.properties.PropertiesStorage;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        try {
            final Properties databaseAuthentication = PropertiesStorage.getDatabaseAuthentication();
            config.setJdbcUrl(databaseAuthentication.getProperty("url"));
            config.setUsername(databaseAuthentication.getProperty("user"));
            config.setPassword(databaseAuthentication.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/unischedule?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            config.setUsername("helios");
            config.setPassword("unischedulesolar");
        }
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DataSource(){}
}