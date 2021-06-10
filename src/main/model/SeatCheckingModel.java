package main.model;

import main.SQLConnection;
import java.sql.*;

public class SeatCheckingModel
{
    String seatname;
    public void addBooking(String username, String date, String time,String seat) throws SQLException
    {
        seatname = seat;

        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String insertFields = "INSERT INTO Booking (username, date, time, seat, approved) VALUES ('" ;
        String insertValues =  username + "','" + date + "','" + time + "','" + seat + "','pending" + "')";
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
        finally
        {
            connectionDB.close();
        }
    }

    public void updateSeat() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String sql = "UPDATE Seat SET booked = true WHERE seatname = '" + seatname + "';";

        try
        {
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
        finally
        {
            connectionDB.close();
        }
    }
}