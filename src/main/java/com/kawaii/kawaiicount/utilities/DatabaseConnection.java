package com.kawaii.kawaiicount.utilities;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    public static Connection connect() throws SQLException
    {
        // STORE DATABASE IN USERS HOME DIRECTORY
        String dbPath = System.getProperty("user.home") + "/KawaiiCount/kawaiicount.db";
        new File(System.getProperty("user.home") + "/KawaiiCount/").mkdirs();

        String url = "jdbc:sqlite:" + dbPath;
        return DriverManager.getConnection(url);
    }

    public static String getDirectory()
    {
        return System.getProperty("user.home") + "/KawaiiCount/";
    }
}
