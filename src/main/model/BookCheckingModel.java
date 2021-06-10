package main.model;

import main.SQLConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;


public class BookCheckingModel
{
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

            boolean checking = false;  //checking variable: to remember if user has any upcoming booking
            boolean hasUsed = false;  //hasUsed variable: to see if user has been booking before(if no then just simply add new without checking)

            //check if username has booked previously
            while (resultSet.next())
            {
                hasUsed = true;
                //get date and time from database
                String data_date = resultSet.getString("date");
                String data_time = resultSet.getString("time");

                if (!checkDateTime(data_date, data_time)) //if false means there is future booking
                {
                    String checkApproved = resultSet.getString("approved");
                    if (checkApproved.equals("pending") || checkApproved.equals("yes"))
                        //if false means this book has been cancelled
                        //true then return: there is a future booking that is waiting to approve
                    {
                        checking = true;
                        break;
                    }
                }
            }

            if (!hasUsed)
            {
                if(checkDateTime(date,time))
                    return false;
                else
                    return true;
            }
            else
            {
                if (checking == true)  //user has upcoming booking
                {
                    return false;
                }
                else
                {
                    if (checkDateTime(date, time)) //checking if the date and time of the next booking is from the past or not
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
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

    public boolean checkDateTime(String date, String time)
    {
        //convert string format to date/time format
        LocalDate date_temp = LocalDate.parse(date);
        LocalTime time_temp = LocalTime.parse(time);

        //get current date and time
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        //compare booking date/time and current date/time
        int timeDiff = time_temp.compareTo(localTime);
        int dateDiff = date_temp.compareTo(localDate);

        //true means this booking is from the past, false means this is future booking.
        if(dateDiff < 0)
            return true;
        else if ((dateDiff == 0) && (timeDiff <= 0))
            return true;
        else
            return false;
    }
}