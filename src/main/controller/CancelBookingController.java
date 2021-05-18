package main.controller;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.controller.HistoryController;
import java.io.IOException;

public class CancelBookingController
{
    HistoryController historyController = new HistoryController();

    // when user want to delete booking
    public void btYes(ActionEvent event) throws IOException
    {
        historyController.deleteBooking();
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
