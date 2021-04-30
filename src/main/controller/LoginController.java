package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.model.LoginModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    public LoginModel loginModel = new LoginModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Hyperlink createAccount;

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
    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent event)
    {
        try
        {
            if (loginModel.isLogin(txtUsername.getText(),txtPassword.getText()))
            {
                isConnected.setText("Logged in successfully");
            }
            else
            {
                isConnected.setText("Invalid username or password. Please try again");
                signUp();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void signUp()
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("ui/signup.fxml"));
            Stage signupStage = new Stage();
            signupStage.setScene(new Scene(root,800, 600));
            signupStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
}
