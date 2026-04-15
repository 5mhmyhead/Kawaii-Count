package com.kawaii.kawaiicount.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    private static final String url = "jdbc:sqlite:src/main/resources/com/kawaii/kawaiicount/database/kawaiicount.db";

    public static Connection connect() throws SQLException
    {
        return DriverManager.getConnection(url);
    }
}
