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

    public void addDatabase(String firstname,String lastname,String role,String username,String password,String secret,String answer) throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String insertFields = "INSERT INTO Employee (firstname, lastname, role, username, password, secret_qs, answer) VALUES ('" ;
        String insertValues =  firstname + "','" + lastname + "','" + role + "','" + username + "','" + password + "','" + secret + "','" + answer + "')";
        String query = insertFields + insertValues;

        try
        {
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
}