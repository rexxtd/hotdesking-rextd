package main.model;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookCheckingModel
{
    //add date and time to database
    public void addDateTime(String username, String date, String time) throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String insertFields = "INSERT INTO Booking (username, date, time) VALUES ('" ;
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

    //checking if user have booking in the future
    public boolean bookingExist(String user, String date, String time) throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "select * from booking where username = ?";
        try
        {
            preparedStatement = connectionDB.prepareStatement(query);
            preparedStatement.setString(1, user);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                return false;
            }

            else
            {
                //convert string format to date format
                LocalDate inp = LocalDate.parse(date);

                //get current date
                LocalDate localDate = LocalDate.now();

                //compare booking date and current date
                if (inp.isEqual(localDate) || inp.isBefore(localDate))
                    return false;
                else
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