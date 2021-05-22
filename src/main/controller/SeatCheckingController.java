package main.controller;

import javafx.event.ActionEvent;
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
    private int lastClickedIndex = -1;
    @FXML
    private Button H1;
    @FXML
    private Button H2;
    @FXML
    private Button H5;
    @FXML
    private Button H6;
    @FXML
    private Button H7;
    @FXML
    private Button H3;
    @FXML
    private Button H4;
    @FXML
    private Button V3;
    @FXML
    private Button V1;
    @FXML
    private Button V2;
    @FXML
    private Button V4;
    @FXML
    private Button V5;
    @FXML
    private Button V6;
    @FXML
    private Button V7;
    @FXML
    private Button V8;
    @FXML
    private Button V9;
    @FXML
    private Label txtSeat;
    @FXML
    private Label txtAvailability;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setUpButtonListeners();
    }

    public void setUpButtonListeners()
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
