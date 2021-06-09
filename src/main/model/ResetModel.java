package main.model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetModel {
    Connection connection;
    public static String account;

    public ResetModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isConfirm(String username, String question, String answer) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from employee where username = ? and secret_qs = ? and answer = ? ";
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, question);
            preparedStatement.setString(3, answer);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                account = username;
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public String getAccount()
    {
        return account;
    }
}