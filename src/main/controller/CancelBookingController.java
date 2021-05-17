package main.controller;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CancelBookingController
{
    // when user want to delete booking
    public void btYes(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // when user cancel delete booking
    public void btCancel(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
