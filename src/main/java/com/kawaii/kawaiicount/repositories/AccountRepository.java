package com.kawaii.kawaiicount.repositories;

import com.kawaii.kawaiicount.objects.Account;
import com.kawaii.kawaiicount.utilities.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository
{
    public Account findByCredentials(String username, String password)
    {
        String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    return new Account(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("account_type")
                    );
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Login query failed: " + e.getMessage());
        }

        return null;
    }

    public boolean existsByUsername(String username)
    {
        String sql = "SELECT 1 FROM Account WHERE username = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery())
            {
                return rs.next();
            }
        }
        catch (SQLException e)
        {
            System.err.println("Username check failed: " + e.getMessage());
        }

        return false;
    }

    public boolean existsByEmail(String email)
    {
        String sql = "SELECT 1 FROM Account WHERE email = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery())
            {
                return rs.next();
            }
        }
        catch (SQLException e)
        {
            System.err.println("Email check failed: " + e.getMessage());
        }

        return false;
    }

    public boolean updatePassword(String email, String newPassword)
    {
        String sql = "UPDATE Account SET password = ? WHERE email = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, newPassword);
            stmt.setString(2, email);

            return stmt.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.err.println("Password update failed: " + e.getMessage());
        }

        return false;
    }

    public boolean createAccount(String username, String password, String email, String accountType)
    {
        String sql = "INSERT INTO Account (username, password, email, account_type) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, accountType);

            stmt.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            System.err.println("Account creation failed: " + e.getMessage());
        }

        return false;
    }
}
