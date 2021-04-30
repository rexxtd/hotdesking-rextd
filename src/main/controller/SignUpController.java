package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.model.LoginModel;
import main.model.SignUpModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable
{
    public LoginModel loginModel = new LoginModel();
    @FXML
    private Label isConnected;
    public SignUpModel signupModel = new SignUpModel();
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtSecret;
    @FXML
    private TextField txtAnswer;
    @FXML
    private Hyperlink hpLogin;
    @FXML
    private Button signUpButton;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if (loginModel.isDbConnected())
        {
            isConnected.setText("Connected");
        }
        else
        {
            isConnected.setText("Not Connected");
        }
    }
}
