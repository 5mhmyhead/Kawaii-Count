package com.kawaii.kawaiicount.utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer
{
    // INITIALIZES THE DATABASE IF IT DOES NOT EXIST
    public static void initialize()
    {
        createProductTable();
        createAccountTable();
        createOrdersTable();
    }

    private static void createProductTable()
    {
        String sql = """
            CREATE TABLE IF NOT EXISTS Product (
                prod_id INTEGER PRIMARY KEY AUTOINCREMENT,
                prod_name TEXT NOT NULL UNIQUE,
                category TEXT NOT NULL CHECK(category IN ('Breakfast', 'Lunch', 'Dinner', 'Appetizer')),
                type TEXT NOT NULL CHECK(type IN ('Vegetarian', 'Non-Vegetarian')),
                prod_price REAL NOT NULL,
                amount_sold REAL DEFAULT 0,
                amount_stock INTEGER DEFAULT 0,
                amount_discount INTEGER DEFAULT 0,
                status TEXT DEFAULT 'Available' CHECK(status IN ('Unavailable', 'Available', 'Out of Stock')),
                image BLOB
            );
            """;

        executeSQL(sql);
    }

    private static void createAccountTable()
    {
        String sql = """
            CREATE TABLE IF NOT EXISTS Account (
                user_id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                account_type TEXT NOT NULL
            );
            """;

        executeSQL(sql);
    }

    private static void createOrdersTable()
    {
        String sql = """
            CREATE TABLE IF NOT EXISTS Orders (
                order_id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER NOT NULL,
                prod_id INTEGER NOT NULL,
                customer_id INTEGER NOT NULL,
                total_amount REAL NOT NULL,
                order_quantity INTEGER NOT NULL,
                order_status TEXT DEFAULT 'Pending' CHECK(order_status IN ('Pending', 'Cancelled', 'Completed')),
                order_date TEXT NOT NULL,
                FOREIGN KEY (user_id) REFERENCES Account(user_id),
                FOREIGN KEY (prod_id) REFERENCES Product(prod_id)
            );
            """;
        executeSQL(sql);
    }

    private static void executeSQL(String sql)
    {
        try (Connection conn = DatabaseConnection.connect(); Statement stmt = conn.createStatement())
        {
            stmt.execute(sql);
        }
        catch (SQLException e)
        {
            System.err.println("Database failed to initialize: " + e.getMessage());
        }
    }
}


