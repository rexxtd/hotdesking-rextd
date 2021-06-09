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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.SQLConnection;

public class SeatCheckingController implements Initializable
{
    private String lastClickedIndex = "";
    @FXML
    private Label txtSeat;
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

    //checking which seat is available
    public void seatCalling() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        PreparedStatement preparedStatement = connectionDB.prepareStatement("select * from seat");
        ResultSet rs = preparedStatement.executeQuery();

        int inc = 0;
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
        }
        else if (e.getSource() == H2)
        {
            lastClickedIndex = "H2";
        }
        else if (e.getSource() == H3)
        {
            lastClickedIndex = "H3";
        }
        else if (e.getSource() == H4)
        {
            lastClickedIndex = "H4";
        }
        else if (e.getSource() == H5)
        {
            lastClickedIndex = "H5";
        }
        else if (e.getSource() == H6)
        {
            lastClickedIndex = "H6";
        }
        else if (e.getSource() == H7)
        {
            lastClickedIndex = "H7";
        }
        else if (e.getSource() == V1)
        {
            lastClickedIndex = "V1";
        }
        else if (e.getSource() == V2)
        {
            lastClickedIndex = "V2";
        }
        else if (e.getSource() == V3)
        {
            lastClickedIndex = "V3";
        }
        else if (e.getSource() == V4)
        {
            lastClickedIndex = "V4";
        }
        else if (e.getSource() == V5)
        {
            lastClickedIndex = "V5";
        }
        else if (e.getSource() == V6)
        {
            lastClickedIndex = "V6";
        }
        else if (e.getSource() == V7)
        {
            lastClickedIndex = "V7";
        }
        else if (e.getSource() == V8)
        {
            lastClickedIndex = "V8";
        }
        else if (e.getSource() == V9)
        {
            lastClickedIndex = "V9";
        }

        txtSeat.setText(lastClickedIndex);
    }

    //cancel booking and return to homepage
    @FXML
    public void Cancel(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //confirm booking and return to homepage
    @FXML
    public void Confirm(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
