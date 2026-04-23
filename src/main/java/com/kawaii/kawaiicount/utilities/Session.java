package com.kawaii.kawaiicount.utilities;

public class Session
{
    // HARDCODED MANAGER PROVIDED PASSWORD
    private static String managerProvidedPW = "vivii";

    private static int userId;
    private static String username;
    private static String userType;

    public static String getManagerProvidedPW()
    {
        return managerProvidedPW;
    }

    public static int getUserId()
    {
        return userId;
    }

    public static String getUsername()
    {
        return username;
    }

    public static String getUserType()
    {
        return userType;
    }

    public static void setManagerProvidedPW(String newManagerProvidedPW)
    {
        managerProvidedPW = newManagerProvidedPW;
    }

    public static void setUserId(int id)
    {
        userId = id;
    }

    public static void setUsername(String user)
    {
        username = user;
    }

    public static void setUserType(String type)
    {
        userType = type;
    }
}
