package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.SQLConnection;

public class SeatCheckingController implements Initializable
{
    private BookCheckingController bcc = new BookCheckingController();
    private int inc = 0;
    private String seat = "";
    private String lastClickedIndex = "";
    @FXML
    private Label txtSeat;
    @FXML
    private Label failMessage;
    @FXML
    private Button H1,H2,H3,H4,H5,H6,H7,V1,V2,V3,V4,V5,V6,V7,V8,V9;

    private String [] seatNameArr = new String[16];
    private Boolean[] avaiArr = new Boolean[16];

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            seatCalling();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //getting data of all the seats(seat name, locked or not)
    public void seatCalling() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        PreparedStatement preparedStatement = connectionDB.prepareStatement("select * from seat");
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next())
        {
            seatNameArr[inc] = rs.getString("seatname");
            avaiArr[inc] = rs.getBoolean("availability");
            inc++;
        }
    }

    // see which seat is clicked
    public void handleButtonAction(ActionEvent e)
    {
        if (e.getSource() == H1)
        {
            lastClickedIndex = "H1";
            inc = 0;
        }
        else if (e.getSource() == H2)
        {
            lastClickedIndex = "H2";
            inc = 1;
        }
        else if (e.getSource() == H3)
        {
            lastClickedIndex = "H3";
            inc = 2;
        }
        else if (e.getSource() == H4)
        {
            lastClickedIndex = "H4";
            inc = 3;
        }
        else if (e.getSource() == H5)
        {
            lastClickedIndex = "H5";
            inc = 4;
        }
        else if (e.getSource() == H6)
        {
            lastClickedIndex = "H6";
            inc = 5;
        }
        else if (e.getSource() == H7)
        {
            lastClickedIndex = "H7";
            inc = 6;
        }
        else if (e.getSource() == V1)
        {
            lastClickedIndex = "V1";
            inc = 7;
        }
        else if (e.getSource() == V2)
        {
            lastClickedIndex = "V2";
            inc = 8;
        }
        else if (e.getSource() == V3)
        {
            lastClickedIndex = "V3";
            inc = 9;
        }
        else if (e.getSource() == V4)
        {
            lastClickedIndex = "V4";
            inc = 10;
        }
        else if (e.getSource() == V5)
        {
            lastClickedIndex = "V5";
            inc = 11;
        }
        else if (e.getSource() == V6)
        {
            lastClickedIndex = "V6";
            inc = 12;
        }
        else if (e.getSource() == V7)
        {
            lastClickedIndex = "V7";
            inc = 13;
        }
        else if (e.getSource() == V8)
        {
            lastClickedIndex = "V8";
            inc = 14;
        }
        else if (e.getSource() == V9)
        {
            lastClickedIndex = "V9";
            inc = 15;
        }

        txtSeat.setText(lastClickedIndex);
        seat = txtSeat.getText();
    }

    public boolean checkAvailability(String seat)
    {
        boolean avai = false;
        for (int i = 0; i < seatNameArr.length; i++)
        {
            if (seatNameArr[i].equals(seat))
            {
                if (avaiArr[i])
                {
                    avai = true;
                    break;
                }
            }
        }
        if (avai) return true;
        else return false;
    }

    public void addBooking(String username, String date, String time,String seat) throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String insertFields = "INSERT INTO Booking (username, date, time, seat, approved) VALUES ('" ;
        String insertValues =  username + "','" + date + "','" + time + "','" + seat + "','pending " + "')";
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

    //confirm booking and return to homepage
    @FXML
    public void Confirm(ActionEvent event) throws IOException, SQLException
    {
        if (!checkAvailability(seat))
        {
            failMessage.setText("This seat is not available,please choose another seat");
        }
        else
        {
            addBooking(bcc.bd_username, bcc.bd_date, bcc.bd_time, seat);
            Parent root = FXMLLoader.load(getClass().getResource("../ui/home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
