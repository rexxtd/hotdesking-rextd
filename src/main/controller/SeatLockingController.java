package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.SQLConnection;
import main.model.SeatLockingModel;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeatLockingController implements Initializable
{
    private SeatLockingModel seatLockingModel = new SeatLockingModel();

    private String seat = "";
    private String lastClickedIndex = "";
    @FXML
    private Label txtSeat;
    @FXML
    private Label failMessage;
    @FXML
    private Label successMessage;
    @FXML
    private Button H1,H2,H3,H4,H5,H6,H7,V1,V2,V3,V4,V5,V6,V7,V8,V9;

    private String [] seatNameArr = new String[16];
    private Boolean[] lockedArr = new Boolean[16];
    ArrayList<Button> buttons = new ArrayList<Button>();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.populateButtonList();
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
        //16 seats -> 16 buttons
        Button[] button = new Button[16];

        for (int i = 0; i < 16; i++)
        {
            // Assign each Button in the list to array element.
            for (Button btn : buttons)
            {
                button[i] = btn;
            }
        }
    }

    public void populateButtonList()
    {
        Field[] fields = SeatLockingController.class.getDeclaredFields();

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

        int inc = 0;
        try
        {
            while (rs.next())
            {
                seatNameArr[inc] = rs.getString("seatname");
                lockedArr[inc] = rs.getBoolean("locked");
                if (lockedArr[inc])
                    buttons.get(inc).setStyle("-fx-background-color: #ef8d22");
                else
                    buttons.get(inc).setStyle("-fx-background-color: #7ab648");
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

    @FXML
    void Lock(ActionEvent event) throws SQLException
    {
        if (seat.equals(""))
        {
            successMessage.setText("");
            failMessage.setText("Please choose a seat");
        }
        else
        {
            seatLockingModel.updateSeat(true, seat);
            seatCalling();
            successMessage.setText("Update successfully !");
            failMessage.setText("");
        }
    }

    @FXML
    void Unlock(ActionEvent event) throws SQLException
    {
        if (seat.equals(""))
        {
            successMessage.setText("");
            failMessage.setText("Please choose a seat");
        }
        else
        {
            seatLockingModel.updateSeat(false, seat);
            seatCalling();
            successMessage.setText("Update successfully !");
            failMessage.setText("");
        }
    }

    @FXML
    void LockAll(ActionEvent event) throws SQLException
    {
        seatLockingModel.updateAll(true);
        seatCalling();
        successMessage.setText("Update successfully !");
        failMessage.setText("");
    }

    @FXML
    void UnlockAll(ActionEvent event) throws SQLException
    {
        seatLockingModel.updateAll(false);
        seatCalling();
        successMessage.setText("Update successfully !");
        failMessage.setText("");
    }

    @FXML
    void COVID(ActionEvent event) throws SQLException
    {
        seatLockingModel.COVID();
        seatCalling();
        successMessage.setText("Update successfully !");
        failMessage.setText("");
    }

    @FXML
    void Exit(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
