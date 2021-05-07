package main.model;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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