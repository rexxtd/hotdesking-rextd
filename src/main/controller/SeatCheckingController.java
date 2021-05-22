package main.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SeatCheckingController implements Initializable
{
    private String lastClickedIndex = "";
    @FXML
    private Label txtSeat;
    @FXML
    private Label txtAvailability;
    @FXML
    private Button H1,H2,H3,H4,H5,H6,H7,V1,V2,V3,V4,V5,V6,V7,V8,V9;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       
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
