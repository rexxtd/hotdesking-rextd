package main.model;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.sql.*;

public class BookCheckingModel
{
    //add date and time to database
    public void addDateTime(String username, String date, String time) throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String insertFields = "INSERT INTO Booking (username, day, time) VALUES ('" ;
        String insertValues =  username + "','" + date + "','" + time + "')";
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
    public boolean bookingExist(String user, String date, String time) throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "select * from booking where username = ? and day = ? and time = ?";
        try
        {
            preparedStatement = connectionDB.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, time);

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