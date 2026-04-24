package com.kawaii.kawaiicount.utilities;

import java.io.*;

public class ManagerPasswordHelper
{
    private static final String FILE_PATH = DatabaseConnection.getDirectory() + "manager.txt";

    public static String load()
    {
        File file = new File(FILE_PATH);

        if (!file.exists())
        {
            save("vivii");
            return "vivii";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String password = reader.readLine();
            return password != null ? password : "vivii";
        }
        catch (IOException e)
        {
            System.err.println("Failed to load manager password: " + e.getMessage());
            return "vivii";
        }
    }

    public static void save(String password)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH)))
        {
            writer.write(password);
        }
        catch (IOException e)
        {
            System.err.println("Failed to save manager password: " + e.getMessage());
        }
    }
}

