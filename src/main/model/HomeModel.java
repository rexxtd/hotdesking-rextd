package main.model;

import main.SQLConnection;

import java.sql.Connection;

public class HomeModel
{
    Connection connection;
    public HomeModel()
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
        catch(Exception e)
        {
            return false;
        }
    }
}