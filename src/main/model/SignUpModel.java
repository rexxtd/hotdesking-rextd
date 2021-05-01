package main.model;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.sql.*;

public class SignUpModel
{
    Connection connection;

    public SignUpModel()
    {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Boolean isDbConnected()
    {
        try
        {
            return !connection.isClosed();
        }
        catch (Exception e)
        {
            return false;
        }
    }
}