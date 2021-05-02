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

import main.model.ResetModel;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResetController implements Initializable
{
    public ResetModel resetModel = new ResetModel();
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
    private Hyperlink hpLogin;
    @FXML
    private Button signUpButton;
    private Stage stage;
    private Boolean check = false;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if (resetModel.isDbConnected())
        {
            isConnected.setText("Connected");
        }
        else
        {
            isConnected.setText("Not Connected");
        }
    }

    //system will confirm if information is correct
    public void Confirm() throws SQLException
    {
        try
        {
            if (resetModel.isConfirm(txtUsername.getText(),txtSecret.getText(),txtAnswer.getText()))
            {
                check = true;
            }
            else
            {
                isConnected.setText("Invalid username or password. Please try again");
                check = false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void resetPage(ActionEvent event) throws IOException
    {
        //Confirm();
        //if (check)
        {
            Parent root = FXMLLoader.load(getClass().getResource("../ui/reset.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    //switch to resetPassword scene
    public void loginPage(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
