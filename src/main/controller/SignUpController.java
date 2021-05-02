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
import main.SQLConnection;

import main.model.SignUpModel;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class SignUpController implements Initializable
{
    public SignUpModel signupModel = new SignUpModel();
    @FXML
    private Label isConnected;
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
    private Label successMessage;
    @FXML
    private Label failMessage;
    private Stage stage;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if (signupModel.isDbConnected())
        {
            isConnected.setText("Connected");
        }
        else
        {
            isConnected.setText("Not Connected");
        }
    }

    //what happen when click sign up button
    public void Signup(ActionEvent event) throws SQLException {
        registerUser();
    }

    //get signup information from user
    public void registerUser() throws SQLException
    {
        String firstname = txtFirstname.getText();
        String lastname = txtLastname.getText();
        String role = txtRole.getText().toLowerCase();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String secret = txtSecret.getText();
        String answer = txtAnswer.getText();

        if (firstname.trim().isEmpty() || lastname.trim().isEmpty() || role.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty() || secret.trim().isEmpty() || answer.trim().isEmpty())
        {
            successMessage.setText(null);
            failMessage.setText("Please provide all information!");
        }

        else
        {
            //check if username existed or not
            if (signupModel.accountExist(username))
            {
                //check if role is correct or not
                if (role.equals("admin") || role.equals("staff"))
                {
                    //add account to database
                    signupModel.addDatabase(firstname, lastname, role, username, password, secret, answer);

                    failMessage.setText(null);
                    successMessage.setText("User has been registered successfully!");
                }
                else
                {
                    successMessage.setText(null);
                    failMessage.setText("Role can only be 'Staff' or 'Admin' !");
                }
            }
            else
            {
                successMessage.setText(null);
                failMessage.setText("Account existed. Please login or reset password!");
            }
        }
    }

    //switch to resetPassword scene
    public void Login(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
