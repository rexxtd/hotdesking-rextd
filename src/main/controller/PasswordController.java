package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import main.model.PasswordModel;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PasswordController implements Initializable
{
    public PasswordModel passwordModel = new PasswordModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtSecret;
    @FXML
    private TextField txtAnswer;
    @FXML
    private Button confirmButton;
    private Stage stage;
    private Boolean check = false;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if (passwordModel.isDbConnected())
        {
            isConnected.setText("Connected");
        }
        else
        {
            isConnected.setText("Not Connected");
        }
    }

    public void loginPage(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}