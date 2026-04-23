package com.kawaii.kawaiicount.objects;

public record Account(int userId, String username, String accountType)
{
    public int getUserId()
    {
        return userId;
    }

    public String getUsername()
    {
        return username;
    }

    public String getAccountType()
    {
        return accountType;
    }
}
