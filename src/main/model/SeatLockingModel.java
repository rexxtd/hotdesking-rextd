package main.model;

import main.SQLConnection;
import java.sql.*;

public class SeatLockingModel
{
    public void updateAll(boolean value) throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String sql = "UPDATE Seat SET locked = " + value + " WHERE id IS NOT NULL;";

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

    public void updateSeat(boolean value, String seatname) throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String sql = "UPDATE Seat SET locked = " + value + " WHERE seatname = '" + seatname + "';";

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

    public void COVID() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String[] lockSeats = {"H2", "H4", "H6", "V2", "V3", "V5", "V7", "V9"};
        String[] unlockSeats = {"H1","H3", "H5", "H7", "V1", "V4", "V6" ,"V8"};

        for (int i=0; i< lockSeats.length; i++)
        {
            String sql1 = "UPDATE Seat Set locked = " + true + " WHERE seatname = '" + lockSeats[i] + "';";
            try
            {
                Statement statement = connectionDB.createStatement();
                statement.executeUpdate(sql1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                e.getCause();
            }
        }
        for (int i=0; i< unlockSeats.length; i++)
        {
            String sql2 = "UPDATE Seat Set locked = " + false + " WHERE seatname = '" + unlockSeats[i] + "';";
            try
            {
                Statement statement = connectionDB.createStatement();
                statement.executeUpdate(sql2);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                e.getCause();
            }
        }
    }
}
