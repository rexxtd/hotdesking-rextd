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

    //add information to database
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

    //checking username exist method
    public boolean accountExist(String username) throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "select * from employee where username = ?";
        try
        {
            preparedStatement = connectionDB.prepareStatement(query);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                return false;
            }
            else
            {
                return true;
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
}