package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.SQLConnection;
import main.model.SeatCheckingModel;

public class SeatCheckingController implements Initializable
{
    private BookCheckingController bcc = new BookCheckingController();
    private SeatCheckingModel seatCheckingModel = new SeatCheckingModel();

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
    private Boolean[] bookedArr = new Boolean[16];
    private Boolean[] lockedArr = new Boolean[16];
    ArrayList<Button> buttons = new ArrayList<Button>();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.populateJButtonList();
        buttonArray();
        try
        {
            seatCalling();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void buttonArray()
    {
        Button[] button = new Button[100];

        for (int i = 0; i < 100; i++)
        {
            // Assign each Button in the list to array element.
            for (Button btn : buttons)
            {
                button[i] = btn;
            }
        }
    }

    public void populateJButtonList()
    {
        Field[] fields = SeatCheckingController.class.getDeclaredFields();

        // Loop over each field to determine if it is a Button.
        for (Field field : fields)
        {
            // If it is a Button then add it to the list.
            if (field.getType().equals(Button.class))
            {
                try
                {
                    // De-reference the field from the object and cast it to a Button and add it to the list.
                    buttons.add((Button) field.get(this));
                }
                catch (IllegalArgumentException | IllegalAccessException | SecurityException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    //getting data of all the seats(seat name, locked or not)
    public void seatCalling() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        PreparedStatement preparedStatement = connectionDB.prepareStatement("select * from seat");
        ResultSet rs = preparedStatement.executeQuery();

        try
        {
            while (rs.next())
            {
                seatNameArr[inc] = rs.getString("seatname");
                bookedArr[inc] = rs.getBoolean("booked");
                lockedArr[inc] = rs.getBoolean("locked");
                if (lockedArr[inc])
                    buttons.get(inc).setStyle("-fx-background-color: #ef8d22");
                else if (bookedArr[inc])
                    buttons.get(inc).setStyle("-fx-background-color: #c92d39");
                inc++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
        finally
        {
            connectionDB.close();
            rs.close();
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
        seat = txtSeat.getText();
    }

    public boolean checkAvailability(String seat)
    {
        boolean avai = true;
        for (int i = 0; i < seatNameArr.length; i++)
        {
            if (seatNameArr[i].equals(seat))
            {
                if (lockedArr[i])  //if true then seat is locked
                {
                    avai = false;
                    break;
                }
                else if (bookedArr[i])  //if true then seat is booked
                {
                    avai = false;
                    break;
                }
            }
        }
        if (avai) return true;
        else return false;
    }

    //confirm booking and return to homepage
    @FXML
    public void Confirm(ActionEvent event) throws IOException, SQLException
    {
        if (seat.equals(""))
        {
            failMessage.setText("Please choose a seat");
        }
        else if (!checkAvailability(seat))
        {
            failMessage.setText("This seat is not available,please choose another seat");
        }

        else
        {
            seatCheckingModel.addBooking(bcc.bd_username, bcc.bd_date, bcc.bd_time, seat);
            seatCheckingModel.updateSeat();
            Parent root = FXMLLoader.load(getClass().getResource("../ui/home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
